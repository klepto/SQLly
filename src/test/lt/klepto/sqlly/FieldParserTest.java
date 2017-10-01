package lt.klepto.sqlly;

import lt.klepto.sqlly.annotation.ColumnName;
import lt.klepto.sqlly.field.FieldMap;
import lt.klepto.sqlly.field.FieldParser;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link FieldParser}.
 *
 * @author Augustinas R.
 */
public class FieldParserTest {

	public void testParseFields() throws Exception {
		FieldParser parser = new FieldParser();
		FieldMap fieldMap = parser.parseFields(TestObject.class);

		Field testIntField = TestObject.class.getField("testInt");
		Field testStringField = TestObject.class.getField("testString");

		assertEquals("someFieldName", fieldMap.get(testIntField));
		assertEquals("testString", fieldMap.get(testStringField));
	}

	private class TestObject {

		@ColumnName("someFieldName")
		private int testInt;

		private String testString;

	}

}