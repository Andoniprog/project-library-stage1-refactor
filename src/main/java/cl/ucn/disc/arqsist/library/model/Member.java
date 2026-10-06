/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A member of the library.
 */
@DatabaseTable(tableName = "members")
public final class Member {

    /** Generated identifier. */
    @DatabaseField(generatedId = true)
    private int id;

    /** Full name. */
    @DatabaseField(canBeNull = false)
    private String name;

    /** Email address. */
    @DatabaseField(canBeNull = false)
    private String email;

    /** Needed by ORMLite and Jackson. */
    public Member() {
    }

    /**
     * Creates a member.
     *
     * @param name the full name.
     * @param email the email address.
     */
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
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
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email.
     *
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
