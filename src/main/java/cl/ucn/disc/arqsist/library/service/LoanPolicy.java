/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;

import java.time.LocalDate;

/**
 * Single place for the loan rules: loan period and overdue fee.
 */
public final class LoanPolicy {

    /** Loan period in days. */
    public static final int DUE_DAYS = 21;

    /** Fee charged for each day past the due date. */
    public static final double FEE_PER_DAY = 1.0;

    /** Not instantiable. */
    private LoanPolicy() {
    }

    /**
     * Computes the due date of a loan.
     *
     * @param loanDate the day the book is lent.
     * @return the day the book must be returned.
     */
    public static LocalDate dueDate(LocalDate loanDate) {
        return loanDate.plusDays(DUE_DAYS);
    }
}
