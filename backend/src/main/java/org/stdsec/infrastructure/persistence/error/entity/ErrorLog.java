package org.stdsec.infrastructure.persistence.error.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "error_log")
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    public String traceId;

    @Column(nullable = false, name = "time_stamp")
    public Instant timestamp;

    @Column(length = 2000)
    public String message;

    public Integer status;
    public String method;
    public String path;
    public String exceptionType;
    public String userId;

}
