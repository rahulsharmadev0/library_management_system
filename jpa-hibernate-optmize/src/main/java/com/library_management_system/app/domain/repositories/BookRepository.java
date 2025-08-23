package com.library_management_system.app.domain.repositories;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import com.library_management_system.app.domain.entities.Book;

public class BookRepository extends JpaRepository {
    public static final String TABLE_NAME = "books";
    private static BookRepository instance;

    private BookRepository() {
        super();
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    public void insert(Book entity) {
        executeWithTransaction(emf -> {
            emf.persist(entity);
        });
    }

    public void update(Book entity) {
        executeWithTransaction(emf -> {
            emf.merge(entity);
        });
    }

    public void delete(int id) {
        executeWithTransaction(emf -> {
            Book book = emf.find(Book.class, id);
            if (book != null) {
                emf.remove(book);
            }
        });
    }

    public List<Book> findByAuthor(String author) {
        return execute(emf -> {
            return emf.createQuery("SELECT b FROM Book b WHERE b.author LIKE :author", Book.class)
                    .setParameter("author", "%" + author + "%")
                    .getResultList();
        });
    }

    public List<Book> findByTitle(String title) {
        return execute(emf -> {
            return emf.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title", Book.class)
                    .setParameter("title", "%" + title + "%")
                    .getResultList();
        });

    }

    public Optional<Book> findByIsbn(String isbn) {
        return execute(emf -> {
            List<Book> books = emf.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                    .setParameter("isbn", isbn)
                    .getResultList();
            if (books.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(books.get(0));
            }
        });
    }

    public List<Book> findAll() {
        return execute(emf -> {
            return emf.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();
        });
    }

    public Optional<Book> findById(int id) {
        return execute(emf -> {
            Book book = emf.find(Book.class, id);
            return Optional.ofNullable(book);
        });
    }
}
