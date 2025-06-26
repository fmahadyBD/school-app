package com.fmahadybd.school_app_service.enums;

public enum SubjectGroup {
    SCIENCE("Science"),
    ARTS("Arts"),
    COMMERCE("Commerce"),
    REGULAR("Regular");

    private final String groupName;

    SubjectGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
