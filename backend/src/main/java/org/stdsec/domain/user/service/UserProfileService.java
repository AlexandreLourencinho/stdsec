package org.stdsec.domain.user.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.stdsec.domain.user.entity.UserProfileEntity;
import org.stdsec.domain.user.exceptions.EmailAlreadyExistsException;
import org.stdsec.domain.user.mapper.UserProfileMapper;
import org.stdsec.domain.user.repository.UserProfileRepository;
import org.stdsec.domain.user.web.dto.CreateUserProfileRequestDTO;
import org.stdsec.domain.user.web.dto.CreateUserProfileResponseDTO;

import java.util.List;

@ApplicationScoped
public class UserProfileService {

    private final UserProfileRepository repository;

    @Inject
    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CreateUserProfileResponseDTO create(CreateUserProfileRequestDTO user) {

        if (repository.findByEmail(user.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email %s is already taken and cannot be registered for another user.", user.email());
        }
        var entity = UserProfileMapper.fromRequest(user);
        repository.persist(entity);
        return UserProfileMapper.toResponse(entity);
    }

    public List<UserProfileEntity> listAll() {
        return repository.listAll();
    }

}
