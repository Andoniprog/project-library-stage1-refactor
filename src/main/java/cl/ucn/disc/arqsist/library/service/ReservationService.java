/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;

import cl.ucn.disc.arqsist.library.dao.BookDao;
import cl.ucn.disc.arqsist.library.dao.LoanDao;
import cl.ucn.disc.arqsist.library.dao.MemberDao;
import cl.ucn.disc.arqsist.library.dao.ReservationDao;
import cl.ucn.disc.arqsist.library.model.Book;
import cl.ucn.disc.arqsist.library.model.Loan;
import cl.ucn.disc.arqsist.library.model.Member;
import cl.ucn.disc.arqsist.library.model.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Reservation use cases: reserve, list and fulfill.
 */
public final class ReservationService {

    /** Persistence of reservations. */
    private final ReservationDao reservationDao;

    /** Persistence of books. */
    private final BookDao bookDao;

    /** Persistence of members. */
    private final MemberDao memberDao;

    /** Persistence of loans. */
    private final LoanDao loanDao;

    /**
     * Creates the service.
     *
     * @param reservationDao the reservation DAO.
     * @param bookDao the book DAO.
     * @param memberDao the member DAO.
     * @param loanDao the loan DAO.
     */
    public ReservationService(ReservationDao reservationDao, BookDao bookDao, MemberDao memberDao, LoanDao loanDao) {
        this.reservationDao = reservationDao;
        this.bookDao = bookDao;
        this.memberDao = memberDao;
        this.loanDao = loanDao;
    }

    /**
     * Reserves a book for a member.
     *
     * @param bookId the book identifier.
     * @param memberId the member identifier.
     * @return the new reservation.
     * @throws SQLException if the database fails.
     */
    public Reservation reserve(int bookId, int memberId) throws SQLException {
        Book book = bookDao.findById(bookId);
        Member member = memberDao.findById(memberId);
        Reservation reservation = new Reservation(member, book, LocalDate.now());
        reservationDao.create(reservation);
        return reservation;
    }

    /**
     * Lists all reservations.
     *
     * @return every reservation.
     * @throws SQLException if the database fails.
     */
    public List<Reservation> findAll() throws SQLException {
        return reservationDao.findAll();
    }

    /**
     * Turns a reservation into a loan using the loan policy.
     *
     * @param reservationId the reservation identifier.
     * @return the new loan.
     * @throws SQLException if the database fails.
     * @throws IllegalStateException if the reservation is missing or already fulfilled.
     */
    public Loan fulfill(int reservationId) throws SQLException {
        Reservation reservation = reservationDao.findById(reservationId);
        if (reservation == null || reservation.isFulfilled()) {
            throw new IllegalStateException("Reservation not available");
        }

        reservation.setFulfilled(true);
        reservationDao.update(reservation);

        LocalDate today = LocalDate.now();
        Loan loan = new Loan(reservation.getMember(), reservation.getBook(), today, LoanPolicy.dueDate(today));
        loanDao.create(loan);
        return loan;
    }
}
