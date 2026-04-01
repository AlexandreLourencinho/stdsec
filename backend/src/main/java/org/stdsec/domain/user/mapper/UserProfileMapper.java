package org.stdsec.domain.user.mapper;

import org.stdsec.domain.user.entity.UserProfileEntity;
import org.stdsec.domain.user.web.dto.CreateUserProfileRequestDTO;
import org.stdsec.domain.user.web.dto.CreateUserProfileResponseDTO;

import java.time.LocalDateTime;

public class UserProfileMapper {

    public static UserProfileEntity fromRequest(CreateUserProfileRequestDTO create) {
        var entity = new UserProfileEntity();
        entity.setCreatedAt(LocalDateTime.now());
        entity.setEmail(create.email());
        entity.setFirstName(create.firstName());
        entity.setLastName(create.lastName());

        return entity;
    }

    public static CreateUserProfileResponseDTO toResponse(UserProfileEntity entity) {
        return new CreateUserProfileResponseDTO(entity.getId(), entity.getEmail(), entity.getFirstName(), entity.getLastName());
    }
}
