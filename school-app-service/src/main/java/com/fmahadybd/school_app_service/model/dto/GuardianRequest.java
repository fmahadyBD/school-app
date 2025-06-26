package com.fmahadybd.school_app_service.model.dto;

import com.fmahadybd.school_app_service.enums.GuardianRelation;
import com.fmahadybd.school_app_service.enums.Religion;

public record GuardianRequest(
        String name,
        String phoneNumber,
        String email,
        String address,
        GuardianRelation relation,
        Religion religion,
        String status
) {
}
