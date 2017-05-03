package lt.klepto.sqlly;

import lt.klepto.sqlly.exception.*;
import lt.klepto.sqlly.field.FieldMap;
import lt.klepto.sqlly.field.FieldParser;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static lt.klepto.sqlly.exception.Exceptions.*;
import static lt.klepto.sqlly.util.ReflectionUtils.*;

/**
 * This is the main class for using Sqlly.
 * <p>To parse {@link ResultSet} into java objects simply invoke {@link #parse(ResultSet, Class)} for multiple rows
 * or {@link #parseRow(ResultSet, Class)} for single row queries.</p>
 *
 * @author Augustinas R.
 */
public class Sqlly {

    private final FieldParser fieldParser;

    /**
     * Initializes Sqlly with default {@link FieldParser}.
     */
    private Sqlly() {
        this.fieldParser = new FieldParser();
    }

    /**
     * Parses {@link ResultSet} into an instance of the target class. {@link ResultSet} must have a position
     * corresponding to one of the rows, e.g. {@link ResultSet#next()} has to be invoked before passing a new
     * {@link ResultSet} to this method.
     *
     * @param resultSet   a ResultSet that needs to be parsed
     * @param targetClass a class for which column values should be assigned
     * @return object reference of a parsed {@link ResultSet} row
     * @throws ColumnNotFoundException       if one of the fields was mapped to non-existing column
     * @throws IllegalRowException           if this ResultSet was positioned at non-existing row
     * @throws IncompatibleDataTypeException if column value and field have different data types
     */
    private <T> T _parseRow(ResultSet resultSet, Class<T> targetClass) throws ColumnNotFoundException, IllegalRowException, IncompatibleDataTypeException {
        requireValidRow(resultSet);

        T instance = createInstance(targetClass);
        FieldMap fieldMap = fieldParser.parseFields(targetClass);

        for (Field field : fieldMap.keySet()) {
            String columnName = fieldMap.get(field);
            requireColumn(resultSet, columnName);

            Object value = null;
            try {
                value = resultSet.getObject(columnName, field.getType());
            } catch (SQLException e) {
                throw new IncompatibleDataTypeException(field, columnName);
            }

            setFieldValue(instance, field, value);
        }

        return instance;
    }

    /**
     * Parses a single {@link ResultSet} row into an instance of the target class.
     *
     * @param resultSet   the ResultSet to be parsed
     * @param targetClass the target class to assign column values to
     * @return {@link Optional} that may contain parsed result of a single row
     */
    public static <T> Optional<T> parseRow(ResultSet resultSet, Class<T> targetClass) {
        try {
            if (resultSet.next()) {
                T parsedResult = sqlly._parseRow(resultSet, targetClass);
                return Optional.of(parsedResult);
            }
        } catch (SqllyException e) {
            throw e;
        } catch (SQLException e) {
            throw new UncheckedException(e);
        }

        return Optional.empty();
    }

    /**
     * Parses all rows of the {@link ResultSet} into instances of the target class.
     *
     * @param resultSet   the ResultSet to be parsed
     * @param targetClass the target class to assign column values to
     * @return {@link Set} containing all parsed results, possibly empty if ResultSet contained no rows.
     */
    public static <T> Set<T> parse(ResultSet resultSet, Class<T> targetClass) {
        List<T> parsedResults = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T parsedResult = sqlly._parseRow(resultSet, targetClass);
                parsedResults.add(parsedResult);
            }
        } catch (SqllyException e) {
            throw e;
        } catch (SQLException e) {
            throw new UncheckedException(e);
        }

        return new HashSet<>(parsedResults);
    }

    /**
     * Default Sqlly instance.
     */
    private static final Sqlly sqlly = new Sqlly();

}