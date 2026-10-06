/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;

/**
 * Signals that a requested entity does not exist.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Creates the exception.
     *
     * @param message the detail message.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
