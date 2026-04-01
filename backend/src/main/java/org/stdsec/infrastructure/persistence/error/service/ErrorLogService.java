package org.stdsec.infrastructure.persistence.error.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.stdsec.infrastructure.persistence.error.entity.ErrorLog;
import org.stdsec.infrastructure.persistence.error.repository.ErrorLogRepository;

@ApplicationScoped
public class ErrorLogService {

    private final ErrorLogRepository repository;

    @Inject
    public ErrorLogService(ErrorLogRepository repository) {
        this.repository = repository;
    }

    public void create(ErrorLog errorLog) {
        repository.persist(errorLog);
    }

}
