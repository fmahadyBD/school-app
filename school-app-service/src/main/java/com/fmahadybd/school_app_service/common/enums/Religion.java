package com.fmahadybd.school_app_service.common.enums;

public enum Religion {
    ISLAM("Islam"),
    HINDU("Hindu"),
    CHRISTIAN("Christian"),
    BUDDHIST("Buddhist"),
    OTHER("Other");

    private final String religionName;

    Religion(String religionName) {
        this.religionName = religionName;
    }

    public String getReligionName() {
        return religionName;
    }
}
