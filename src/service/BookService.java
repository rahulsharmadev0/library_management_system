package service;
import model.Book;

public class BookService  extends LocalDB<Book>{
    private static final String FILE_PATH = "book.dat";
    public static BookService instance = new BookService();
  
    private BookService(){
        super( FILE_PATH, Book::fromCsv);
    }
}
