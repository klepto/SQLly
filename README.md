# SQLly
SQLly provides a simple way to map your JDBC ResultSet into Java objects. Define a class, pass ResultSet to SQLly and get Java objects in return. It's that simple.

&nbsp;

# Examples

Let's say we are fetching books from a database.
First of all let's define a class that represents a single book.
```java
public  class Book {

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
```

&nbsp;

Execute our query and parse the results.

```java
Statement statement = connection.prepareStatement("SELECT book_id, name, author FROM books");
ResultSet resultSet = statement.executeQuery();

Set<Book> books = Sqlly.parse(resultSet, Book.class);
```
&nbsp;


For single row results we can simply call:
```java
Optional<Book> optional = Sqlly.parseRow(resultSet, Book.class);
```


&nbsp;

# Copyright

Lightstone is open-source software released under the GNU general public license, please see the LICENSE file for details.