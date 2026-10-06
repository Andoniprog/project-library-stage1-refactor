/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;

import cl.ucn.disc.arqsist.library.dao.BookDao;
import cl.ucn.disc.arqsist.library.dao.LoanDao;
import cl.ucn.disc.arqsist.library.model.Book;
import cl.ucn.disc.arqsist.library.model.Loan;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Loan use cases: listing, returning and overdue detection.
 */
public final class LoanService {

    /** Persistence of loans. */
    private final LoanDao loanDao;

    /** Persistence of books. */
    private final BookDao bookDao;

    /**
     * Creates the service.
     *
     * @param loanDao the loan DAO.
     * @param bookDao the book DAO.
     */
    public LoanService(LoanDao loanDao, BookDao bookDao) {
        this.loanDao = loanDao;
        this.bookDao = bookDao;
    }

    /**
     * Lists all loans.
     *
     * @return every loan.
     * @throws SQLException if the database fails.
     */
    public List<Loan> findAll() throws SQLException {
        return loanDao.findAll();
    }

    /**
     * Returns a loan and charges the fee when it is late.
     *
     * @param loanId the loan identifier.
     * @return the loan, or null if it does not exist or was already returned.
     * @throws SQLException if the database fails.
     */
    public Loan returnLoan(int loanId) throws SQLException {
        Loan loan = loanDao.findById(loanId);
        if (loan == null || loan.isReturned()) {
            return loan;
        }

        loan.setReturned(true);
        loan.setReturnDate(LocalDate.now());

        LocalDate due = loan.getDueDate();
        LocalDate today = LocalDate.now();
        if (today.isAfter(due)) {
            long daysOverdue = ChronoUnit.DAYS.between(due, today);
            loan.setOverdueFee(daysOverdue * LoanPolicy.FEE_PER_DAY);
        }

        loanDao.update(loan);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookDao.update(book);

        return loan;
    }

    /**
     * Lists the open loans that are past their due date.
     *
     * @return the overdue loans.
     * @throws SQLException if the database fails.
     */
    public List<Loan> overdueLoans() throws SQLException {
        LocalDate today = LocalDate.now();
        return loanDao.findAll().stream()
                .filter(l -> !l.isReturned() && l.getDueDate().isBefore(today))
                .toList();
    }
}
