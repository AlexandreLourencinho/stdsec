package org.stdsec.web.error;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.stdsec.controller.error.StdErrorMessage;

import java.time.Instant;
import java.util.UUID;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    private static final Logger log = Logger.getLogger(NotFoundExceptionHandler.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(NotFoundException exception) {

        var errorTraceId = UUID.randomUUID().toString();
        log.warnf("%s: Resource not found at %s", errorTraceId, uriInfo.getPath());
        var error = new StdErrorMessage(
                Instant.now(),
                404,
                "Not Found",
                "RESOURCE_NOT_FOUND",
                "The requested resource was not found",
                uriInfo.getPath(),
                errorTraceId
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }

}
