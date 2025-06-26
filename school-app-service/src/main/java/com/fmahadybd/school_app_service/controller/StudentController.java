package com.fmahadybd.school_app_service.controller;

import com.fmahadybd.school_app_service.model.domain.Student;
import com.fmahadybd.school_app_service.model.dto.StudentRequestDTO;
import com.fmahadybd.school_app_service.model.dto.StudentUpdateDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fmahadybd.school_app_service.model.response.ApiResponse;
import com.fmahadybd.school_app_service.services.StudentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "Get all students")
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllStudents(
            @ParameterObject Pageable pageable
    ) {
        List<Student> students = studentService.getAllStudents(pageable);
        ApiResponse response = new ApiResponse("Students retrieved successfully", true, students, 200);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Save Student")
    @PostMapping("/")
    public ResponseEntity<ApiResponse> saveStudent(
            @RequestPart(value = "student") StudentRequestDTO studentRequestDto,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {
        Long id = studentService.saveStudent(studentRequestDto, profilePicture);
        ApiResponse response = new ApiResponse("Student saved successfully", true, id, 201);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Delete Student with the id")
    @DeleteMapping("/{id}")  // Fixed the path variable mapping
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ApiResponse("Student deleted successfully", true, null, 200));
    }

    @Operation(summary = "Update student ")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStudent(
            @PathVariable Long id,
            StudentUpdateDTO studentUpdateDTO
    ){
        studentService.updateStudent(id, studentUpdateDTO);
        return ResponseEntity.ok(new ApiResponse("Student updated successfully", true, id, 200));
    }

    @Operation(summary = "Get student by id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long id){
        Student student = studentService.getStudentById(id);
        ApiResponse response = new ApiResponse("Student retrieved successfully", true, student, 200);
        return ResponseEntity.ok(response);
    }
}
