package lt.klepto.sqlly.util;

import lt.klepto.sqlly.exception.UncheckedException;

import java.lang.reflect.Field;

/**
 * Utility methods for {@link java.lang.reflect} package.
 *
 * @author Augustinas R.
 */
public class ReflectionUtils {

    /**
     * Sets value to a field regardless of the field accessibility.
     *
     * @param instance the object instance for which field value is being modified
     * @param field    the declared field
     * @param value    the value to assign to the field
     */
    public static void setFieldValue(Object instance, Field field, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(instance, value);
            field.setAccessible(accessible);
        } catch (IllegalAccessException e) {
            /*
             * Ignored since exception will never be thrown.
             */
        }
    }

    /**
     * Creates a new instance of a given class.
     *
     * @param targetClass the class to be instanced
     * @return a new class instance
     */
    public static <T> T createInstance(Class<T> targetClass) {
        try {
            return targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new UncheckedException(e);
        }
    }

}
