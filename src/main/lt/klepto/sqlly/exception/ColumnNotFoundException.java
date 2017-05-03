package lt.klepto.sqlly.exception;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * Thrown when {@link ResultSet} doesn't contain specified column name for a {@link Field}.
 *
 * @author Augustinas R.
 */
public final class ColumnNotFoundException extends SqllyException {

    public ColumnNotFoundException(String columnName) {
        super("ResultSet didn't contain column '" + columnName + "'.");
    }

}