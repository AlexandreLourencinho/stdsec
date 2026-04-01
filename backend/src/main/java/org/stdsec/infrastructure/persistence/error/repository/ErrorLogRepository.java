package org.stdsec.infrastructure.persistence.error.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.stdsec.infrastructure.persistence.error.entity.ErrorLog;

@ApplicationScoped
public class ErrorLogRepository implements PanacheRepository<ErrorLog> {
}
