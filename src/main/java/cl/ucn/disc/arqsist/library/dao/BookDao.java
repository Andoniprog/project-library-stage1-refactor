/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.dao;

import cl.ucn.disc.arqsist.library.model.Book;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Persistence of {@link Book} entities.
 */
public final class BookDao extends BaseDao<Book> {

    /**
     * Creates the DAO.
     *
     * @param connectionSource the database connection.
     */
    public BookDao(ConnectionSource connectionSource) {
        super(connectionSource, Book.class);
    }
}
