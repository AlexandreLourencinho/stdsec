package org.stdsec.web.error;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.stdsec.controller.error.StdErrorMessage;
import org.stdsec.exceptions.BaseException;

import java.time.Instant;
import java.util.UUID;

@Provider
public class RestExceptionHandler implements ExceptionMapper<BaseException> {

    private static final Logger log = Logger.getLogger(RestExceptionHandler.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(BaseException e) {

        var errorTraceId = UUID.randomUUID().toString();

        log.errorf("%s: An error happened during the call of %s. Error code is : %s, reason and status are: %s, %s",
                errorTraceId, uriInfo.getPath(), e.getCode(), e.getStatus().getReasonPhrase(), e.getStatus().getStatusCode(), e);

        var error = new StdErrorMessage(Instant.now(),
                e.getStatus().getStatusCode(),
                e.getStatus().getReasonPhrase(),
                e.getCode(),
                e.getMessage(),
                uriInfo.getPath(),
                errorTraceId);
        return Response.status(e.getStatus()).entity(error).build();
    }
}
