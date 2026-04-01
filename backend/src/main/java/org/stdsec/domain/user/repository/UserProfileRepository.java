package org.stdsec.domain.user.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.stdsec.domain.user.entity.UserProfileEntity;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserProfileRepository implements PanacheRepository<UserProfileEntity> {

    public Optional<UserProfileEntity> findByKeycloakId(UUID keycloakId) {
        return find("keycloakId", keycloakId).firstResultOptional();
    }

    public Optional<UserProfileEntity> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

}
