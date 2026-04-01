package org.stdsec.infrastructure.persistence.audit.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.stdsec.infrastructure.persistence.audit.entity.AuditLog;
import org.stdsec.infrastructure.persistence.audit.repository.AuditRepository;

@ApplicationScoped
public class AuditLogService {

    private final AuditRepository repository;

    @Inject
    public AuditLogService(AuditRepository repository) {
        this.repository = repository;
    }

    public void create(AuditLog auditLog) {
        repository.persist(auditLog);
    }

}
