package lt.klepto.sqlly;

import lt.klepto.sqlly.annotation.ColumnName;
import org.junit.Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Sqlly}.
 *
 * @author Augustinas R.
 */
public class SqllyTest {

    @Test
    public void testParseRow() throws Exception {
        ResultSet resultSet = mockResultSet();

        Optional<Book> bookOp = Sqlly.parseRow(resultSet, Book.class);
        assertEquals(true, bookOp.isPresent());

        Book book = bookOp.get();
        assertEquals(1, book.getId());
        assertEquals("The Hunger Games", book.getName());
        assertEquals("Suzanne Collins", book.getAuthor());
    }

    @Test
    public void testParse() throws  Exception {
        ResultSet resultSet = mockResultSet();

        Set<Book> books = Sqlly.parse(resultSet, Book.class);
        assertEquals(2, books.size());
    }

    private static ResultSet mockResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        when(resultSet.getObject("book_id", int.class))
                .thenReturn(1)
                .thenReturn(2);

        when(resultSet.getObject("name", String.class))
                .thenReturn("The Hunger Games")
                .thenReturn("To Kill a Mockingbird ");

        when(resultSet.getObject("author", String.class))
                .thenReturn("Suzanne Collins")
                .thenReturn("Harper Lee");

        when(resultSet.findColumn("book_id"))
                .thenReturn(0);
        when(resultSet.findColumn("name"))
                .thenReturn(1);
        when(resultSet.findColumn("author"))
                .thenReturn(2);

        return resultSet;
    }

    public static class Book {

        @ColumnName("book_id")
        private int id;

        private String name;
        private String author;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

    }

}