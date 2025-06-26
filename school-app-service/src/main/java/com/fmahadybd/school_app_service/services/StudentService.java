package com.fmahadybd.school_app_service.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fmahadybd.school_app_service.mapper.StudentMapper;
import com.fmahadybd.school_app_service.model.domain.Student;
import com.fmahadybd.school_app_service.model.dto.StudentRequestDTO;
import com.fmahadybd.school_app_service.model.dto.StudentUpdateDTO;
import com.fmahadybd.school_app_service.persistence.repository.StudentEntityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fmahadybd.school_app_service.persistence.entity.StudentEntity;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentEntityRepository studentEntityRepository;
    private final StudentMapper studentMapper;

    @Value("${upload-dir}")
    private String uploadDir;

    @Value("${max-file-size:5242880}")
    private long maxFileSize;

    // limit the extension
    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of("jpg", "jpeg", "png");

    public List<Student> getAllStudents(Pageable pageable) {
        try {
            List<StudentEntity> studentEntities = studentEntityRepository.findAll(pageable).getContent();
            return studentEntities.stream()
                    .map(studentMapper::entryToDomain)
                    .toList();
        } catch (Exception e) {
            log.error("Error retrieving students: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve students", e);
        }
    }

    public Long saveStudent(StudentRequestDTO studentRequestDTO, MultipartFile profilePicture) {
        try {
            // Validate input
            if (studentRequestDTO == null) {
                throw new IllegalArgumentException("Student request cannot be null");
            }

            StudentEntity studentEntity = studentMapper.dtoToEntity(studentRequestDTO);

            if (profilePicture != null && !profilePicture.isEmpty()) {
                validateImage(profilePicture);
                String imageFileName = saveImage(profilePicture, studentEntity);
                studentEntity.setProfilePicture(imageFileName);
            }

            StudentEntity savedEntity = studentEntityRepository.save(studentEntity);
            log.info("Student saved successfully with ID: {}", savedEntity.getId());
            return savedEntity.getId();

        } catch (IllegalArgumentException e) {
            log.error("Invalid argument while saving student: {}", e.getMessage(), e);
            throw e; // Re-throw as-is since it's already properly typed
        } catch (Exception e) {
            log.error("Unexpected error while saving student: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save student: " + e.getMessage(), e);
        }
    }

    public Student getStudentById(Long id) {
        StudentEntity studentEntity= studentEntityRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found student by this id"));
        return studentMapper.entryToDomain(studentEntity);
    }

    public Student updateStudent(Long id, StudentUpdateDTO studentUpdateDTO){
        StudentEntity studentEntity= studentEntityRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found student by this id"));

        StudentEntity updatedStudentEntity = studentMapper.updateDTOToEntity(studentUpdateDTO);
        return studentMapper.entryToDomain(updatedStudentEntity);
    }


    private void validateImage(MultipartFile file) {
        try {
            // Check if file is null or empty
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("File cannot be null or empty");
            }

            // Check file size
            if (file.getSize() > maxFileSize) {
                throw new IllegalArgumentException("File size exceeds the maximum limit of " + maxFileSize + " bytes");
            }

            // Check file extension
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.trim().isEmpty()) {
                throw new IllegalArgumentException("File name is empty or null");
            }

            String extension = StringUtils.getFilenameExtension(originalFileName);
            if (extension == null || !ALLOWED_IMAGE_TYPES.contains(extension.toLowerCase())) {
                throw new IllegalArgumentException("Invalid file type. Allowed types are: " + ALLOWED_IMAGE_TYPES);
            }

            // Check content type
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Invalid content type. Must be an image file");
            }

        } catch (Exception e) {
            log.error("Image validation failed: {}", e.getMessage(), e);
            if (e instanceof IllegalArgumentException) {
                throw e;
            }
            throw new RuntimeException("Failed to validate image: " + e.getMessage(), e);
        }
    }

    public Long deleteStudent(Long studentId) {
        try {
            // Validate input
            if (studentId == null) {
                throw new IllegalArgumentException("Student ID cannot be null");
            }

            StudentEntity studentEntity = studentEntityRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));

            // Delete associated image if exists
            if (studentEntity.getProfilePicture() != null && !studentEntity.getProfilePicture().trim().isEmpty()) {
                try {
                    deleteStudentImage(studentEntity.getProfilePicture());
                } catch (Exception e) {
                    log.warn("Failed to delete image file for student {}: {}", studentId, e.getMessage());
                    // Continue with student deletion even if image deletion fails
                }
            }

            studentEntityRepository.delete(studentEntity);
            log.info("Student deleted successfully with ID: {}", studentId);
            return studentEntity.getId();

        } catch (EntityNotFoundException e) {
            log.error("Student not found for deletion: {}", e.getMessage(), e);
            throw e; // Re-throw as-is
        } catch (IllegalArgumentException e) {
            log.error("Invalid argument for student deletion: {}", e.getMessage(), e);
            throw e; // Re-throw as-is
        } catch (Exception e) {
            log.error("Unexpected error while deleting student with ID {}: {}", studentId, e.getMessage(), e);
            throw new RuntimeException("Failed to delete student with ID " + studentId + ": " + e.getMessage(), e);
        }
    }

    private String saveImage(MultipartFile profilePicture, StudentEntity student) {
        try {
            // Create upload directory if it does not exist
            Path uploadPath = Paths.get(uploadDir + "/students");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("Created upload directory: {}", uploadPath);
            }

            // Get original file extension
            String originalFileName = profilePicture.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(originalFileName);

            // Validate student name
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Student name cannot be null or empty for image saving");
            }

            // Create safe filename with extension
            String safeStudentName = student.getName().trim().replaceAll("[^a-zA-Z0-9\\s]", "_");
            String fileName = safeStudentName + "_" + UUID.randomUUID().toString() + "." + extension;
            Path filePath = uploadPath.resolve(fileName);

            // Use REPLACE_EXISTING to overwrite existing files
            Files.copy(profilePicture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("Image saved successfully: {}", fileName);

            return fileName;

        } catch (IOException e) {
            log.error("IO error while saving image: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save image file: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            log.error("Invalid argument while saving image: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while saving image: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
        }
    }

    private void deleteStudentImage(String fileName) {
        try {
            if (fileName != null && !fileName.trim().isEmpty()) {
                Path filePath = Paths.get(uploadDir + "/students/" + fileName);
                boolean deleted = Files.deleteIfExists(filePath);
                if (deleted) {
                    log.info("Image deleted successfully: {}", fileName);
                } else {
                    log.warn("Image file not found for deletion: {}", fileName);
                }
            }
        } catch (IOException e) {
            log.error("IO error while deleting image {}: {}", fileName, e.getMessage(), e);
            throw new RuntimeException("Failed to delete image file: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error while deleting image {}: {}", fileName, e.getMessage(), e);
            throw new RuntimeException("Failed to delete image: " + e.getMessage(), e);
        }
    }
}