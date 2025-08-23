package domain.repositories;

import java.sql.*;
import java.util.List;


import java.util.Optional;

import domain.entities.Book;

public class BookRepository extends JdbcRepository<Book> {
    public static final String TABLE_NAME = "book";
    private static BookRepository instance;

    private BookRepository() throws SQLException {
        super();
    }

    public static BookRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Book mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("pages"));
    }

    public int insert(Book entity) throws SQLException {
        return executeUpdate(
                "INSERT INTO " + TABLE_NAME + " (title, author, isbn, pages) VALUES (?, ?, ?, ?)",
                entity.title(), entity.author(), entity.isbn(), entity.pages());
    }

    public int update(Book entity) throws SQLException {
        return executeUpdate(
                "UPDATE " + TABLE_NAME + " SET title = ?, author = ?, isbn = ?, pages = ? WHERE id = ?",
                entity.title(), entity.author(), entity.isbn(), entity.pages(), entity.id());
    }

    public int delete(int id) throws SQLException {
        return executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE id = ?", id);
    }

    public List<Book> findByAuthor(String author) throws SQLException {
        return executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE author = ?", author);
    }

    public List<Book> findByTitle(String title) throws SQLException {
        return executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE title LIKE ?", "%" + title + "%");
    }

    public Optional<Book> findByIsbn(String isbn) throws SQLException {
        return findOne("SELECT * FROM " + TABLE_NAME + " WHERE isbn = ?", isbn);
    }
}
