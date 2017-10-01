package lt.klepto.sqlly;

import lt.klepto.sqlly.util.ReflectionUtils;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link ReflectionUtils}.
 *
 * @author Augustinas R.
 */
public class ReflectionUtilsTest {

	@Test
	public void testSetFieldValue() throws Exception {
		TestObject object = new TestObject();
		Field field = object.getClass().getField("testField");

		ReflectionUtils.setFieldValue(object, field, 1);
		assertEquals(1, object.getTestField());
	}

	@Test
	public void testCreateInstance() {
		TestObject object = ReflectionUtils.createInstance(TestObject.class);
		assertNotNull(object);
	}

	private class TestObject {

		private int testField;

		public int getTestField() {
			return testField;
		}

	}

}