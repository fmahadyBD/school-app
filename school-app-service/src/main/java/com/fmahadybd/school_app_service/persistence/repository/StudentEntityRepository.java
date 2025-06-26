package com.fmahadybd.school_app_service.persistence.repository;

import com.fmahadybd.school_app_service.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
}
