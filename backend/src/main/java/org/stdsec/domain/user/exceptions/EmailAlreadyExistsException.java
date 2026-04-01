package org.stdsec.domain.user.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message, Object ...args) {
        super(String.format(message, args));
    }

}
