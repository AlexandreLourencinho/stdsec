package org.stdsec.controller.error;

import java.time.Instant;

public record StdErrorMessage(Instant timestamp, int status,
                              String error, String code, String message,
                              String path, String traceId) {
}
