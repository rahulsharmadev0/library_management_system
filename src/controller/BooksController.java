// package controller;

// import model.Book;
// import service.BookService;

// import java.util.ArrayList;
// import java.util.List;

// public class BooksController {
//     private final BookService service = new BookService( );
    
//     private BooksController() {
//     }

//     private static BooksController instance;

//     public static BooksController getInstance() {
//         if (instance == null) {
//             instance = new BooksController();
//         }
//         return instance;
//     }

//     public List<Book> getBooks() { 
//         return new ArrayList<>(books); // Return defensive copy
//     }

//     public void addBook(Book book) {
//         if (book == null) {
//             throw new IllegalArgumentException("Book cannot be null");
//         }
//         books.add(book);
//     }
    
//     public boolean removeBook(int index) {
//         if (index >= 0 && index < books.size()) {
//             books.remove(index);
//             return true;
//         }
//         return false;
//     }
    
  
    
//     public Book getBook(int index) {
//         if (index >= 0 && index < books.size()) {
//             return books.get(index);
//         }
//         return null;
//     }
    
//     public int getTotalBooks() {
//         return books.size();
//     }
// }
