/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;

import cl.ucn.disc.arqsist.library.dao.BookDao;
import cl.ucn.disc.arqsist.library.model.Book;

import java.util.List;

/**
 * Catalog use cases. It is the only owner of the copy count of a book.
 */
public final class BookService {

    /** Persistence of books. */
    private final BookDao dao;

    /**
     * Creates the service.
     *
     * @param dao the book DAO.
     */
    public BookService(BookDao dao) {
        this.dao = dao;
    }

    /**
     * Lists all books.
     *
     * @return every book.
     */
    public List<Book> listAll() {
        return dao.findAll();
    }

    /**
     * Finds a book by identifier.
     *
     * @param id the book identifier.
     * @return the book, or null if it does not exist.
     */
    public Book findById(int id) {
        return dao.findById(id);
    }

    /**
     * Stores a new book with all its copies available.
     *
     * @param book the book to store.
     * @return the stored book.
     */
    public Book create(Book book) {
        book.setAvailableCopies(book.getTotalCopies());
        dao.create(book);
        return book;
    }

    /**
     * Takes one copy out of the inventory.
     *
     * @param bookId the book identifier.
     * @throws NotFoundException if the book does not exist.
     * @throws IllegalStateException if the book has no available copies.
     */
    public void borrow(int bookId) {
        Book book = dao.findById(bookId);
        if (book == null) {
            throw new NotFoundException("Book not found: " + bookId);
        }
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies of book " + bookId);
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        dao.update(book);
    }

    /**
     * Puts one copy back into the inventory.
     *
     * @param bookId the book identifier.
     * @throws NotFoundException if the book does not exist.
     */
    public void returnCopy(int bookId) {
        Book book = dao.findById(bookId);
        if (book == null) {
            throw new NotFoundException("Book not found: " + bookId);
        }
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        dao.update(book);
    }
}
