/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A book of the catalog.
 */
@DatabaseTable(tableName = "books")
public final class Book {

    /** Generated identifier. */
    @DatabaseField(generatedId = true)
    private int id;

    /** Book title. */
    @DatabaseField(canBeNull = false)
    private String title;

    /** Book author. */
    @DatabaseField(canBeNull = false)
    private String author;

    /** International Standard Book Number. */
    @DatabaseField(canBeNull = false)
    private String isbn;

    /** Copies the library owns. */
    @DatabaseField(canBeNull = false)
    private int totalCopies;

    /** Copies not currently on loan. */
    @DatabaseField(canBeNull = false)
    private int availableCopies;

    /** Needed by ORMLite and Jackson. */
    public Book() {
    }

    /**
     * Creates a book with all its copies available.
     *
     * @param title the book title.
     * @param author the book author.
     * @param isbn the ISBN.
     * @param totalCopies the copies the library owns.
     */
    public Book(String title, String author, String isbn, int totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the identifier.
     *
     * @param id the identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author.
     *
     * @return the author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the ISBN.
     *
     * @return the ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN.
     *
     * @param isbn the ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the total number of copies.
     *
     * @return the total number of copies.
     */
    public int getTotalCopies() {
        return totalCopies;
    }

    /**
     * Sets the total number of copies.
     *
     * @param totalCopies the total number of copies.
     */
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    /**
     * Gets the number of available copies.
     *
     * @return the number of available copies.
     */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Sets the number of available copies.
     *
     * @param availableCopies the number of available copies.
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
