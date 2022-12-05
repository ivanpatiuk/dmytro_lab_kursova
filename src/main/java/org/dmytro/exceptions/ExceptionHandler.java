package org.dmytro.exceptions;

import lombok.extern.log4j.Log4j;

@Log4j
public class ExceptionHandler {
    public static <T extends Exception> void throwNewException(final T exception) throws Exception {
        log.error(exception.getMessage());
        // TODO add email sending
        throw exception;
    }
}
