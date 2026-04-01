package org.stdsec.web.error;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.stdsec.controller.error.StdErrorMessage;

import java.time.Instant;
import java.util.UUID;

@Provider
public class DBConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger log = Logger.getLogger(DBConstraintExceptionHandler.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException e) {

        var errorTraceId = UUID.randomUUID().toString();
        log.errorf("%s: An error happened during the call of %s. Error code is : %s, reason and status are: %s, %s",
                errorTraceId, uriInfo.getPath(), e);
        var error = new StdErrorMessage(
                Instant.now(),
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                "DATABASE_CONSTRAINT",
                "Database constraint violated",
                uriInfo.getPath(),
                errorTraceId
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }

}
