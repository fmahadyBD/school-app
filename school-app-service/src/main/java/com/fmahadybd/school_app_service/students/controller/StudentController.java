package com.fmahadybd.school_app_service.students.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fmahadybd.school_app_service.students.entity.Student;
import com.fmahadybd.school_app_service.students.response.ApiResponse;
import com.fmahadybd.school_app_service.students.service.StudentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-all-students")
    public ResponseEntity<ApiResponse> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
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

    // @PostMapping("/save-student")
    // public String postMethodName(@RequestBody String entity) {
    // //TODO: process POST request

    // return entity;
    // }

}
