package com.fmahadybd.school_app_service.students.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fmahadybd.school_app_service.students.entity.Student;
import com.fmahadybd.school_app_service.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }



}
