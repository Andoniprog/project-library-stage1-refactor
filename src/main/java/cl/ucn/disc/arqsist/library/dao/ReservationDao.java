/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.dao;

import cl.ucn.disc.arqsist.library.model.Reservation;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Persistence of {@link Reservation} entities.
 */
public final class ReservationDao extends BaseDao<Reservation> {

    /**
     * Creates the DAO.
     *
     * @param connectionSource the database connection.
     */
    public ReservationDao(ConnectionSource connectionSource) {
        super(connectionSource, Reservation.class);
    }
}
