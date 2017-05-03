package lt.klepto.sqlly.exception;

import lt.klepto.sqlly.Sqlly;

import java.sql.SQLException;

/**
 * Thrown when SQLException occurs within the SQLly library.
 *
 * @author Augustinas R.
 */
public final class UncheckedException extends SqllyException {

    public UncheckedException(Exception exception) {
        super(exception);
    }

}