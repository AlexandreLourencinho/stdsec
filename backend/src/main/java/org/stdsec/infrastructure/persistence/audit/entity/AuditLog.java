package org.stdsec.infrastructure.persistence.audit.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "audit_log")
public class AuditLog extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "time_stamp")
    private Instant timestamp;
    private String userId;

    @Column(nullable = false)
    private String actions; // CREATE_USER, DELETE_ORDER...
    private String resourceType; // "user", "order"
    private String resourceId;   // "123"
    private String path;
    private String method;

    @Column(length = 2000)
    private String details; // JSON ou texte libre

    public Long getId() {
        return id;
    }

    public AuditLog setId(Long id) {
        this.id = id;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public AuditLog setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public AuditLog setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getActions() {
        return actions;
    }

    public AuditLog setActions(String actions) {
        this.actions = actions;
        return this;
    }

    public String getResourceType() {
        return resourceType;
    }

    public AuditLog setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public AuditLog setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public AuditLog setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public AuditLog setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public AuditLog setDetails(String details) {
        this.details = details;
        return this;
    }

}