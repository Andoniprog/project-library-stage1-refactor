/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library;

import cl.ucn.disc.arqsist.library.dao.BookDao;
import cl.ucn.disc.arqsist.library.dao.LoanDao;
import cl.ucn.disc.arqsist.library.dao.MemberDao;
import cl.ucn.disc.arqsist.library.db.Database;
import cl.ucn.disc.arqsist.library.model.Book;
import cl.ucn.disc.arqsist.library.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Checks that a failed checkout leaves no partial write.
 */
class TransactionBugTest {

    /** Book DAO used to inspect the inventory. */
    private BookDao bookDao;
    /** Service under test. */
    private MemberService memberService;

    /**
     * Creates an in-memory database and the services.
     *
     * @throws Exception if the setup fails.
     */
    @BeforeEach
    void setUp() throws Exception {
        Database db = new Database("jdbc:sqlite::memory:");
        bookDao = new BookDao(db.connectionSource());
        MemberDao memberDao = new MemberDao(db.connectionSource());
        LoanDao loanDao = new LoanDao(db.connectionSource());
        memberService = new MemberService(memberDao, bookDao, loanDao);
    }

    /**
     * A checkout with a bad member must not change availableCopies.
     *
     * @throws Exception if the DAO fails.
     */
    @Test
    void checkoutLeavesNoPartialStateOnFailure() throws Exception {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2);
        bookDao.create(book);

        assertThrows(Exception.class, () -> memberService.checkout(9999, book.getId()));

        Book reloaded = bookDao.findById(book.getId());
        assertEquals(reloaded.getTotalCopies(), reloaded.getAvailableCopies(),
                "availableCopies was decremented even though the loan was never created");
    }
}
