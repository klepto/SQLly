package lt.klepto.sqlly.exception;

import lt.klepto.sqlly.Sqlly;

import java.util.Objects;

/**
 * An abstract exception to be used within SQLly library.
 *
 * @author Augustinas R.
 */
public abstract class SqllyException extends RuntimeException {

    public SqllyException(String message) {
        super(Objects.requireNonNull(message));
    }

    public SqllyException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SqllyException(Throwable throwable) {
        super(throwable);
    }

}