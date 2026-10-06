/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.db;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * ORMLite persister that stores a {@link LocalDate} as an ISO-8601 string.
 */
public final class LocalDatePersister extends BaseDataType {

    /** The single instance, as ORMLite requires. */
    private static final LocalDatePersister INSTANCE = new LocalDatePersister();

    /** Registers the SQL type STRING and the Java type LocalDate. */
    private LocalDatePersister() {
        super(SqlType.STRING, new Class<?>[]{LocalDate.class});
    }

    /**
     * Gives the instance ORMLite looks up by reflection.
     *
     * @return the singleton persister.
     */
    public static LocalDatePersister getSingleton() {
        return INSTANCE;
    }

    /**
     * Parses a default value from the field annotation.
     *
     * @param fieldType the field being configured.
     * @param defaultStr the default value text.
     * @return the same text.
     */
    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultStr) {
        return defaultStr;
    }

    /**
     * Reads the column as text.
     *
     * @param fieldType the field being read.
     * @param results the result set.
     * @param columnPos the column position.
     * @return the column text.
     * @throws SQLException if the column cannot be read.
     */
    @Override
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos)
            throws SQLException {
        return results.getString(columnPos);
    }

    /**
     * Converts the stored text to a date.
     *
     * @param fieldType the field being read.
     * @param sqlArg the stored text.
     * @param columnPos the column position.
     * @return the parsed {@link LocalDate}.
     */
    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {
        return LocalDate.parse((String) sqlArg);
    }

    /**
     * Converts a date to the text that is stored.
     *
     * @param fieldType the field being written.
     * @param javaObject the {@link LocalDate} value.
     * @return the ISO-8601 text.
     */
    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) {
        return javaObject.toString();
    }
}
