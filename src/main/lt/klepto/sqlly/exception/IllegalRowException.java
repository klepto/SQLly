package lt.klepto.sqlly.exception;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Thrown when {@link ResultSet} is positioned at non-existing row.
 *
 * @author Augustinas R.
 */
public final class IllegalRowException extends SqllyException {

    public IllegalRowException(ResultSet resultSet) throws SQLException {
        super("ResultSet is positioned at non-existing row index: " + resultSet.getRow());
    }
}
