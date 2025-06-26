package com.fmahadybd.school_app_service.enums;

public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    private final String sexName;

    Sex(String sexName) {
        this.sexName = sexName;
    }

    public String getSexName() {
        return sexName;
    }
}
// Note: This enum can be used to represent the sex od the students, teachers, or any other entities