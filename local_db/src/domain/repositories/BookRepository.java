package domain.repositories;
import domain.entities.Book;
import domain.services.LocalDB;

public class BookRepository  extends LocalDB<Book>{
    private static final String FILE_PATH = "book.dat";
    public static BookRepository instance = new BookRepository();
  
    private BookRepository(){
        super( FILE_PATH, Book::fromCsv);
    }
}
