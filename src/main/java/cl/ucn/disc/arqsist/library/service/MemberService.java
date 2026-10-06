/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;

import cl.ucn.disc.arqsist.library.dao.BookDao;
import cl.ucn.disc.arqsist.library.dao.LoanDao;
import cl.ucn.disc.arqsist.library.dao.MemberDao;
import cl.ucn.disc.arqsist.library.model.Book;
import cl.ucn.disc.arqsist.library.model.Loan;
import cl.ucn.disc.arqsist.library.model.Member;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Member use cases. Stage 2 moves {@link #checkout(int, int)} into the loan service.
 */
public final class MemberService {

    /** Persistence of members. */
    private final MemberDao memberDao;

    /** Persistence of books. */
    private final BookDao bookDao;

    /** Persistence of loans. */
    private final LoanDao loanDao;

    /**
     * Creates the service.
     *
     * @param memberDao the member DAO.
     * @param bookDao the book DAO.
     * @param loanDao the loan DAO.
     */
    public MemberService(MemberDao memberDao, BookDao bookDao, LoanDao loanDao) {
        this.memberDao = memberDao;
        this.bookDao = bookDao;
        this.loanDao = loanDao;
    }

    /**
     * Registers a member.
     *
     * @param member the member to store.
     * @return the stored member.
     * @throws SQLException if the database fails.
     */
    public Member register(Member member) throws SQLException {
        memberDao.create(member);
        return member;
    }

    /**
     * Lists all members.
     *
     * @return every member.
     * @throws SQLException if the database fails.
     */
    public List<Member> findAll() throws SQLException {
        return memberDao.findAll();
    }

    /**
     * Lends a book to a member using the loan policy.
     *
     * @param memberId the member identifier.
     * @param bookId the book identifier.
     * @return the new loan.
     * @throws SQLException if the database fails.
     */
    public Loan checkout(int memberId, int bookId) throws SQLException {
        Member member = memberDao.findById(memberId);
        Book book = bookDao.findById(bookId);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookDao.update(book);

        LocalDate today = LocalDate.now();
        Loan loan = new Loan(member, book, today, LoanPolicy.dueDate(today));
        loanDao.create(loan);
        return loan;
    }
}
