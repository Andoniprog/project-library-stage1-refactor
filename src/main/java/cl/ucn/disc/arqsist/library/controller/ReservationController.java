/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.controller;

import cl.ucn.disc.arqsist.library.service.ReservationService;
import io.javalin.config.JavalinConfig;

import java.util.Objects;

/**
 * HTTP routes of the reservations.
 */
public final class ReservationController {

    /** Reservation use cases. */
    private final ReservationService service;

    /**
     * Creates the controller.
     *
     * @param service the reservation service.
     */
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    /**
     * Registers the reservation routes.
     *
     * @param config the Javalin configuration.
     */
    public void register(JavalinConfig config) {
        config.routes.post("/reservations", ctx -> {
            int memberId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("memberId")));
            int bookId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("bookId")));
            ctx.json(service.reserve(bookId, memberId));
        });
        config.routes.get("/reservations", ctx -> ctx.json(service.findAll()));
        config.routes.post("/reservations/{id}/fulfill", ctx -> ctx.json(service.fulfill(Integer.parseInt(ctx.pathParam("id")))));
    }
}
