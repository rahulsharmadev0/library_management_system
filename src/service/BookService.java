package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Book;

public class BookService {

   public static BookService instance = new BookService();


    private final String FILE_PATH = "book.dat";
    private List<Book> books = new ArrayList<>();

    
    private BookService() {
        loadBooksFromFile();
    }
    
    private void loadBooksFromFile() {
        try (BufferedReader fr = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = fr.readLine()) != null) {
                books.add(Book.fromCsv(line));
            }
        } catch (IOException e) {
            // File might not exist yet, which is okay
            System.out.println("Info: Could not load books: " + e.getMessage());
        }
    }

    public List<Book> getBooks(){
        return books;
    }
    
    public void addBook(Book book) throws IOException {
        books.add(book);
        saveToFile();
    }

    public Book removeBook(int index) throws IOException{
        Book book = books.remove(index);
        saveToFile();
        return book;
    }
    public void updateBook(Book book) throws IOException {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).isbn().equals(book.isbn())) {
                books.set(i, book);
                saveToFile();
                return;
            }
        }
        // Book with given ISBN not found
        throw new IllegalArgumentException("Book with ISBN " + book.isbn() + " not found");
    }
    
    private void saveToFile() throws IOException {
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Book book : books) {
                fw.write(book.toCsv());
                fw.newLine();
            }
        }
    }
}
