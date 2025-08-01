package domain.repositories;

import java.sql.*;
import java.util.List;

import domain.entities.Book;

public class BookRepository extends JdbcRepository<Book> {
    private static BookRepository instance;
    
    public static BookRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    private BookRepository() throws SQLException {
        super();
    }

    public Book findById(int id) throws SQLException {
        List<Book> results = executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = ?", id);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    protected Book mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getString("pages"));
    }

    private static final String TABLE_NAME = "book";

    public List<Book> getAll() throws SQLException {
        return executeQuery("SELECT * FROM " + TABLE_NAME);
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
}
