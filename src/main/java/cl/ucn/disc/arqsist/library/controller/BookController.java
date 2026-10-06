/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.controller;

import cl.ucn.disc.arqsist.library.dao.BookDao;
import cl.ucn.disc.arqsist.library.model.Book;
import cl.ucn.disc.arqsist.library.service.BookService;
import io.javalin.config.JavalinConfig;

/**
 * HTTP routes of the book catalog.
 */
public final class BookController {

    /** Book use cases. */
    private final BookService service;

    /** Book persistence. */
    private final BookDao dao;

    /**
     * Creates the controller.
     *
     * @param service the book service.
     * @param dao the book DAO.
     */
    public BookController(BookService service, BookDao dao) {
        this.service = service;
        this.dao = dao;
    }

    /**
     * Registers the book routes.
     *
     * @param config the Javalin configuration.
     */
    public void register(JavalinConfig config) {
        config.routes.get("/books", ctx -> ctx.json(dao.findAll()));
        config.routes.get("/books/{id}", ctx -> ctx.json(service.findById(Integer.parseInt(ctx.pathParam("id")))));
        config.routes.post("/books", ctx -> ctx.json(service.create(ctx.bodyAsClass(Book.class))));
    }
}
