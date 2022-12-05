package org.dmytro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaladCreatingException extends RuntimeException{
    private String message;
}
