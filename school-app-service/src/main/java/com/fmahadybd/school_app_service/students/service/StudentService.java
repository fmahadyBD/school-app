package com.fmahadybd.school_app_service.students.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fmahadybd.school_app_service.students.entity.Student;
import com.fmahadybd.school_app_service.students.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Value("${upload-dir}")
    private String uploadDir;

    @Value("${max-file-size:5242880}")
    private long maxFileSize;

    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of("jpg", "jpeg", "png");

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student, MultipartFile profilePicture) throws IOException {
        if (profilePicture != null && !profilePicture.isEmpty()) {
            validateImage(profilePicture);
            String imageFileName = saveImage(profilePicture, student);
            student.setProfilePicture(imageFileName);
        }

        studentRepository.save(student);
    }

    private void validateImage(MultipartFile file) throws IOException {

        // check file size
        if (file.getSize() > maxFileSize) {
            throw new IOException("File size exceeds the maximum limit of " + maxFileSize + " bytes");
        }

        // Check file extention
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IOException("File name is empty or null");
        }

        String extentions = StringUtils.getFilenameExtension(originalFileName);
        if (extentions == null || !ALLOWED_IMAGE_TYPES.contains(extentions.toLowerCase())) {
            throw new IOException("Invalid file type. Allowed types are: " + ALLOWED_IMAGE_TYPES);
        }

        // Check content type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("Invalid content type. Allowed types are: image/*");
        }

    }

    public void deleteStudent(Long studentId) throws IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        if (student.getProfilePicture() != null) {
            deleteStudentImage(student.getProfilePicture());
        }

        studentRepository.delete(student);
    }

    private String saveImage(MultipartFile profilePicture, Student student) throws IOException {

        // create upload directory if it does not exist
        Path uploadPath = Paths.get(uploadDir + "/students");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);

        }

        // Get Original file extention

        String originalFileName = profilePicture.getOriginalFilename();
        String extention = StringUtils.getFilenameExtension(originalFileName);

        // create safe filename with extention
        String safeStudentName = student.getName().replaceAll("[^a-zA-Z0-9\\s]", "_");
        String fileName = safeStudentName + "_" + UUID.randomUUID().toString() + "." + extention;
        Path filePath = uploadPath.resolve(fileName);

        // Use REPLACE_EXISTING to overwrite existing files
        Files.copy(profilePicture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public void deleteStudentImage(String fileName) throws IOException {

        if (fileName != null && !fileName.isEmpty()) {
            Path filePath = Paths.get(uploadDir + "/students/" + fileName);
            Files.deleteIfExists(filePath);
        }
    }

}
