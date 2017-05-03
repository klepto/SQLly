package lt.klepto.sqlly.field;

import lt.klepto.sqlly.annotation.ColumnName;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Field parser parses a {@link Class} into mapping between {@link Field} and a {@link ResultSet} column name.
 *
 * @author Augustinas R.
 */
public class FieldParser {

    private Map<Class<?>, FieldMap> parsedClasses = new HashMap<>();

    /**
     * Maps all fields of a class to column names. If {@link ColumnName} annotation is present, field gets
     * mapped to the value provided in the annotation. Otherwise field name itself is used as a column name.
     *
     * @param targetClass a class containing all the fields that need to be parsed
     * @return instance of a {@link FieldMap}, that contains mapping between fields and column names
     */
    public FieldMap parseFields(Class<?> targetClass) {
        if (!parsedClasses.containsKey(targetClass)) {
            FieldMap fieldMap = new FieldMap();

            for (Field field : targetClass.getDeclaredFields()) {
                String columnName = field.getName();

                ColumnName columnNameAnnotation = field.getAnnotation(ColumnName.class);
                if (columnNameAnnotation != null) {
                    columnName = columnNameAnnotation.value();
                }

                fieldMap.put(field, columnName);
            }

            parsedClasses.put(targetClass, fieldMap);
        }

        return parsedClasses.get(targetClass);
    }


}