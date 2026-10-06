/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.model;

import cl.ucn.disc.arqsist.library.db.LocalDatePersister;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;

/**
 * A reservation of one book by one member.
 */
@DatabaseTable(tableName = "reservations")
public final class Reservation {

    /** Generated identifier. */
    @DatabaseField(generatedId = true)
    private int id;

    /** Member who reserved the book. */
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Member member;

    /** Reserved book. */
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Book book;

    /** Day the reservation was made. */
    @DatabaseField(canBeNull = false, persisterClass = LocalDatePersister.class)
    private LocalDate reservedAt;

    /** True once the reservation became a loan. */
    @DatabaseField
    private boolean fulfilled;

    /** Needed by ORMLite and Jackson. */
    public Reservation() {
    }

    /**
     * Creates a pending reservation.
     *
     * @param member the member who reserves.
     * @param book the reserved book.
     * @param reservedAt the day of the reservation.
     */
    public Reservation(Member member, Book book, LocalDate reservedAt) {
        this.member = member;
        this.book = book;
        this.reservedAt = reservedAt;
        this.fulfilled = false;
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
     * Gets the member.
     *
     * @return the member who reserved the book.
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the member.
     *
     * @param member the member who reserved the book.
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Gets the book.
     *
     * @return the reserved book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book.
     *
     * @param book the reserved book.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets the reservation date.
     *
     * @return the day the reservation was made.
     */
    public LocalDate getReservedAt() {
        return reservedAt;
    }

    /**
     * Sets the reservation date.
     *
     * @param reservedAt the day the reservation was made.
     */
    public void setReservedAt(LocalDate reservedAt) {
        this.reservedAt = reservedAt;
    }

    /**
     * Tells if the reservation became a loan.
     *
     * @return true if fulfilled.
     */
    public boolean isFulfilled() {
        return fulfilled;
    }

    /**
     * Marks the reservation as fulfilled or pending.
     *
     * @param fulfilled true if fulfilled.
     */
    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}
