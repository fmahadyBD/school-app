package com.fmahadybd.school_app_service.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmahadybd.school_app_service.students.entity.Student;

public interface StudentRepository  extends JpaRepository<Student, Long> {
    
    
    
}
