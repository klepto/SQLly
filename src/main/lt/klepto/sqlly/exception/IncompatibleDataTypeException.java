package lt.klepto.sqlly.exception;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * Thrown when {@link ResultSet} column and {@link Field} are different data types.
 *
 * @author Augustinas R.
 */
public final class IncompatibleDataTypeException extends SqllyException {

    public IncompatibleDataTypeException(Field field, String columnName) {
        super("Field '" + field.getName() + "' and column '" + columnName + "' appear to be different data types.");
    }

}
