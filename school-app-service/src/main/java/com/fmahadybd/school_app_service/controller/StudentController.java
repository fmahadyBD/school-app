package com.fmahadybd.school_app_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fmahadybd.school_app_service.persistence.entity.StudentEntity;
import com.fmahadybd.school_app_service.model.response.ApiResponse;
import com.fmahadybd.school_app_service.services.StudentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-all-students")
    public ResponseEntity<ApiResponse> getAllStudents() {
        try {
            List<StudentEntity> students = studentService.getAllStudents();
            ApiResponse response = new ApiResponse("Students retrived Successfully", true, students, 200);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Failed to retrieve students", false, null, 500);
            return ResponseEntity.status(500).body(response);
        }
    }

    // @GetMapping("/get-students-by-name")
    // public ResponseEntity<List<Student>> getMethodName(@RequestParam String name)
    // {
    // return ResponseEntity.ok().body(List.of(new Student()));
    // }

    // @GetMapping("/get-student-by-id")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

    // @GetMapping("/get-students-by-class")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

    // @GetMapping("/get-students-by-class-with-section")
    // public String getMethodName(@RequestParam String param) {
    // return new String();

    // }

    // @GetMapping("/get-students-by-class-with-section-and-group")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

    // @GetMapping("/get-total-students-by-class")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

    // @GetMapping("/get-total-students-by-class-with-section")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

    // @GetMapping("/get-total-students-by-class-with-section-and-group")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

    // @GetMapping("/get-students-by-guardian-name")
    // public ResponseEntity<List<Student>> getStudentsByGuardianName(@RequestParam
    // String guardianName) {
    // // Logic to fetch students by guardian name
    // return ResponseEntity.ok().body(List.of(new Student()));
    // }

    @PostMapping("/save-student")
    public ResponseEntity<ApiResponse> saveStudent(
            @RequestPart(value = "student") StudentEntity student,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {
        try {
            studentService.saveStudent(student, profilePicture);
            ApiResponse response = new ApiResponse("Student saved successfully", true, null, 201);
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Failed to save student", false, null, 500);
            return ResponseEntity.status(500).body(response);
        }
    }

}
