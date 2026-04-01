package org.stdsec.web.error;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.stdsec.controller.error.StdErrorMessage;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Provider
public class ConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger log = Logger.getLogger(ConstraintExceptionHandler.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException e) {

        var errorTraceId = UUID.randomUUID().toString();

        var violations = e.getConstraintViolations()
                .stream()
                .map(v -> Map.of(
                        "field", v.getPropertyPath().toString(),
                        "message", v.getMessage()
                ))
                .toList();

        log.errorf("%s: An error happened during the call of %s. Error code is : %s, reason and status are: %s, %s",
                errorTraceId, uriInfo.getPath(), e);

        var error = new StdErrorMessage(
                Instant.now(),
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                "VALIDATION_ERROR",
                "Validation failed",
                uriInfo.getPath(),
                errorTraceId
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of(
                        "error", error,
                        "violations", violations
                ))
                .build();
    }

}
