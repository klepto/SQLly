package lt.klepto.sqlly.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that indicates which {@link java.sql.ResultSet} column should assign value to this field.
 *
 * @author Augustinas R.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ColumnName {

    /**
     * @return the column name of that contains the value of the field
     */
    String value();

}
