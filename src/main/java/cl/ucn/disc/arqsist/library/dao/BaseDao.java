/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Shared CRUD code and transaction helper of the DAOs.
 *
 * @param <T> the entity type.
 */
public abstract class BaseDao<T> {

    /** ORMLite DAO that does the real work. */
    protected final Dao<T, Integer> dao;

    /**
     * Creates the DAO.
     *
     * @param connectionSource the database connection.
     * @param clazz the entity class.
     * @throws RuntimeException if ORMLite cannot create the DAO.
     */
    protected BaseDao(ConnectionSource connectionSource, Class<T> clazz) {
        try {
            this.dao = DaoManager.createDao(connectionSource, clazz);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lists all entities.
     *
     * @return every stored entity.
     * @throws RuntimeException if the database fails.
     */
    public List<T> findAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds an entity by identifier.
     *
     * @param id the identifier.
     * @return the entity, or null if it does not exist.
     * @throws RuntimeException if the database fails.
     */
    public T findById(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserts an entity.
     *
     * @param entity the entity to store.
     * @throws RuntimeException if the database fails.
     */
    public void create(T entity) {
        try {
            dao.create(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates an entity.
     *
     * @param entity the entity to update.
     * @throws RuntimeException if the database fails.
     */
    public void update(T entity) {
        try {
            dao.update(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes an entity.
     *
     * @param entity the entity to delete.
     * @throws RuntimeException if the database fails.
     */
    public void delete(T entity) {
        try {
            dao.delete(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs the callable in one transaction and keeps the domain error after a rollback.
     *
     * @param callable the work to run.
     * @param <R> the result type.
     * @return the result of the callable.
     * @throws SQLException if the transaction fails for a database reason.
     */
    public <R> R transaction(Callable<R> callable) throws SQLException {
        try {
            return TransactionManager.callInTransaction(dao.getConnectionSource(), callable);
        } catch (SQLException e) {
            if (e.getCause() instanceof RuntimeException cause) {
                throw cause;
            }
            throw e;
        }
    }
}
