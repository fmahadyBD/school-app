package com.fmahadybd.school_app_service.mapper;

import com.fmahadybd.school_app_service.model.domain.Student;
import com.fmahadybd.school_app_service.model.dto.StudentRequestDTO;
import com.fmahadybd.school_app_service.model.dto.StudentUpdateDTO;
import com.fmahadybd.school_app_service.persistence.entity.StudentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    // It's for return
    public Student entryToDomain(StudentEntity entity){
        Student domain = new Student();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }

    // Dto to Entity
    public StudentEntity dtoToEntity(StudentRequestDTO studentRequestDTO){
        StudentEntity entity = new StudentEntity();
        BeanUtils.copyProperties(studentRequestDTO, entity);
        return entity;
    }

    public StudentEntity domainToEntity(Student domain){
        StudentEntity domainEntity = new StudentEntity();
        BeanUtils.copyProperties(domain, domainEntity);
        return domainEntity;
    }
    
    public StudentEntity updateDTOToEntity(StudentUpdateDTO studentUpdateDTO){
        StudentEntity entity = new StudentEntity();
        BeanUtils.copyProperties(studentUpdateDTO, entity);
        return entity;
    }
}
