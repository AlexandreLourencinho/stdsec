package org.stdsec.web.error;

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
public class UnexpectedExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger log = Logger.getLogger(UnexpectedExceptionHandler.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable e) {

        var errorTraceId = UUID.randomUUID().toString();

        log.errorf(e,
                "%s: Unexpected error during call of %s",
                errorTraceId,
                uriInfo.getPath()
        );

        var error = new StdErrorMessage(
                Instant.now(),
                500,
                "Internal Server Error",
                "UNEXPECTED_ERROR",
                "An unexpected error occurred",
                uriInfo.getPath(),
                errorTraceId
        );

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }

}
