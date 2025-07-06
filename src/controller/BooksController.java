package controller;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksController {
    private final ArrayList<Book> books;
    
    private BooksController() {
        // TODO: Load Books from file storage
        books = new ArrayList<>();
    }

    private static BooksController instance;

    public static BooksController getInstance() {
        if (instance == null) {
            instance = new BooksController();
        }
        return instance;
    }

    public List<Book> getBooks() { 
        return new ArrayList<>(books); // Return defensive copy
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        books.add(book);
    }
    
    public boolean removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean removeBook(Book book) {
        return books.remove(book);
    }
    
    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        }
        return null;
    }
    
    public int getTotalBooks() {
        return books.size();
    }
}
