package com.jab.burger.jabburger.Exceptions;

/**
 * Excepción lanzada cuando se intenta crear un usuario que ya existe.
 */
public class UserAlreadyExistsException extends RuntimeException {
    /**
     * Constructor de la excepción.
     * @param message el mensaje de error.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
