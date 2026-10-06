/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.db;

import cl.ucn.disc.arqsist.library.model.Book;
import cl.ucn.disc.arqsist.library.model.Loan;
import cl.ucn.disc.arqsist.library.model.Member;
import cl.ucn.disc.arqsist.library.model.Reservation;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Opens the database, creates the tables and loads the seed data.
 */
public final class Database {

    /** Logger of the class. */
    private static final Logger log = LoggerFactory.getLogger(Database.class);

    /** Connection to the database. */
    private final ConnectionSource connectionSource;

    /**
     * Opens the database and creates the missing tables.
     *
     * @param jdbcUrl the JDBC url of the database.
     * @throws SQLException if the connection or the table creation fails.
     */
    public Database(String jdbcUrl) throws SQLException {
        this.connectionSource = new JdbcConnectionSource(jdbcUrl);
        TableUtils.createTableIfNotExists(connectionSource, Book.class);
        TableUtils.createTableIfNotExists(connectionSource, Member.class);
        TableUtils.createTableIfNotExists(connectionSource, Loan.class);
        TableUtils.createTableIfNotExists(connectionSource, Reservation.class);
    }

    /**
     * Gives the connection used by the DAOs.
     *
     * @return the connection source.
     */
    public ConnectionSource connectionSource() {
        return connectionSource;
    }

    /**
     * Loads the seed data into every empty table. It is safe to call twice.
     * The seed has three books, three members, one reservation and three loans
     * (returned, active and overdue). Only the active and the overdue loan take a copy.
     *
     * @throws SQLException if the database fails.
     */
    public void seedIfEmpty() throws SQLException {
        Dao<Book, Integer> bookDao = DaoManager.createDao(connectionSource, Book.class);
        if (bookDao.queryForAll().isEmpty()) {
            log.debug("Seeding books");
            bookDao.create(new Book("Clean Code", "Robert C. Martin", "9780132350884", 3));
            bookDao.create(new Book("The Pragmatic Programmer", "Hunt & Thomas", "9780201616224", 2));
            bookDao.create(new Book("Design Patterns", "Gamma et al.", "9780201633610", 4));
        }

        Dao<Member, Integer> memberDao = DaoManager.createDao(connectionSource, Member.class);
        if (memberDao.queryForAll().isEmpty()) {
            log.debug("Seeding members");
            memberDao.create(new Member("Ada Lovelace", "ada@example.com"));
            memberDao.create(new Member("Grace Hopper", "grace@example.com"));
            memberDao.create(new Member("Alan Turing", "alan@example.com"));
        }

        Dao<Loan, Integer> loanDao = DaoManager.createDao(connectionSource, Loan.class);
        if (loanDao.queryForAll().isEmpty()) {
            List<Book> books = bookDao.queryForAll();
            List<Member> members = memberDao.queryForAll();
            LocalDate today = LocalDate.now();

            log.debug("Seeding the returned loan");
            Loan returned = new Loan(members.getFirst(), books.getFirst(), today.minusDays(30), today.minusDays(9));
            returned.setReturned(true);
            returned.setReturnDate(today.minusDays(10));
            loanDao.create(returned);

            log.debug("Seeding the active loan");
            Book activeBook = books.get(2);
            Loan active = new Loan(members.get(1), activeBook, today.minusDays(2), today.plusDays(19));
            loanDao.create(active);
            activeBook.setAvailableCopies(activeBook.getAvailableCopies() - 1);
            bookDao.update(activeBook);

            log.debug("Seeding the overdue loan");
            Book overdueBook = books.get(1);
            Loan overdue = new Loan(members.get(2), overdueBook, today.minusDays(30), today.minusDays(9));
            loanDao.create(overdue);
            overdueBook.setAvailableCopies(overdueBook.getAvailableCopies() - 1);
            bookDao.update(overdueBook);
        }

        Dao<Reservation, Integer> reservationDao = DaoManager.createDao(connectionSource, Reservation.class);
        if (reservationDao.queryForAll().isEmpty()) {
            List<Book> books = bookDao.queryForAll();
            List<Member> members = memberDao.queryForAll();
            LocalDate today = LocalDate.now();

            log.debug("Seeding the reservation");
            reservationDao.create(new Reservation(members.getFirst(), books.get(1), today.minusDays(1)));
        }
    }
}
