package org.stdsec.infrastructure.persistence.audit.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.stdsec.infrastructure.persistence.audit.entity.AuditLog;

@ApplicationScoped
public class AuditRepository implements PanacheRepository<AuditLog> {
}
