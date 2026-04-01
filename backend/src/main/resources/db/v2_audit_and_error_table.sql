create table error_log (
    id BIGSERIAL PRIMARY KEY,
    trace_id VARCHAR(255) NOT NULL UNIQUE,
    time_stamp TIMESTAMP NOT NULL,
    status INTEGER,
    method VARCHAR(10),
    path VARCHAR(255),
    exception_type VARCHAR(255),
    message VARCHAR(2000),
    user_id VARCHAR(255)
)

create table audit_log (
    id BIGSERIAL PRIMARY KEY,
    time_stamp TIMESTAMP NOT NULL,
    user_id VARCHAR(255),
    actions VARCHAR(255),
    resource_type VARCHAR(255),
    resource_id VARCHAR(255),
    path VARCHAR(255),
    method VARCHAR(10),
    details VARCHAR(2000)
)