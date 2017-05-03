package lt.klepto.sqlly.exception;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility methods for throwing SQLly exceptions.
 *
 * @author Augustinas R.
 */
public class Exceptions {

    public static void requireValidRow(ResultSet resultSet) {
        try {
            if (resultSet.isBeforeFirst() || resultSet.isAfterLast()) {
                throw new IllegalRowException(resultSet);
            }
        } catch (SQLException e) {
            throw new UncheckedException(e);
        }
    }


    public static void requireColumn(ResultSet resultSet, String columnName) throws ColumnNotFoundException {
        try {
            resultSet.findColumn(columnName);
        } catch (SQLException e) {
            throw new ColumnNotFoundException(columnName);
        }
    }

}