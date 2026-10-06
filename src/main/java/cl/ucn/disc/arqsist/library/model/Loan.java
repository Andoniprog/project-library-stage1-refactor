/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.model;

import cl.ucn.disc.arqsist.library.db.LocalDatePersister;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;

/**
 * A loan of one book to one member.
 */
@DatabaseTable(tableName = "loans")
public final class Loan {

    /** Generated identifier. */
    @DatabaseField(generatedId = true)
    private int id;

    /** Member who borrowed the book. */
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Member member;

    /** Borrowed book. */
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Book book;

    /** Day the book was lent. */
    @DatabaseField(canBeNull = false, persisterClass = LocalDatePersister.class)
    private LocalDate loanDate;

    /** Day the book must be returned. */
    @DatabaseField(canBeNull = false, persisterClass = LocalDatePersister.class)
    private LocalDate dueDate;

    /** Day the book was returned, or null while the loan is open. */
    @DatabaseField(persisterClass = LocalDatePersister.class)
    private LocalDate returnDate;

    /** True once the book is returned. */
    @DatabaseField
    private boolean returned;

    /** Fee for the days past the due date. */
    @DatabaseField
    private double overdueFee;

    /** Needed by ORMLite and Jackson. */
    public Loan() {
    }

    /**
     * Creates an open loan.
     *
     * @param member the member who borrows.
     * @param book the borrowed book.
     * @param loanDate the day the book is lent.
     * @param dueDate the day the book must be returned.
     */
    public Loan(Member member, Book book, LocalDate loanDate, LocalDate dueDate) {
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = false;
        this.overdueFee = 0.0;
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
     * @return the member who borrowed the book.
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the member.
     *
     * @param member the member who borrowed the book.
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Gets the book.
     *
     * @return the borrowed book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book.
     *
     * @param book the borrowed book.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets the loan date.
     *
     * @return the day the book was lent.
     */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /**
     * Sets the loan date.
     *
     * @param loanDate the day the book was lent.
     */
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Gets the due date.
     *
     * @return the day the book must be returned.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date.
     *
     * @param dueDate the day the book must be returned.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the return date.
     *
     * @return the day the book was returned, or null if still open.
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date.
     *
     * @param returnDate the day the book was returned.
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Tells if the book was returned.
     *
     * @return true if the loan is closed.
     */
    public boolean isReturned() {
        return returned;
    }

    /**
     * Marks the loan as returned or open.
     *
     * @param returned true if the loan is closed.
     */
    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    /**
     * Gets the overdue fee.
     *
     * @return the fee for the days past the due date.
     */
    public double getOverdueFee() {
        return overdueFee;
    }

    /**
     * Sets the overdue fee.
     *
     * @param overdueFee the fee for the days past the due date.
     */
    public void setOverdueFee(double overdueFee) {
        this.overdueFee = overdueFee;
    }
}
