package com.fmahadybd.school_app_service.enums;

public enum GuardianRelation {
    FATHER("Father"),
    MOTHER("Mother"),
    GUARDIAN("Guardian"),
    OTHER("Other");

    private final String relationName;

    GuardianRelation(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationName() {
        return relationName;
    }
}
