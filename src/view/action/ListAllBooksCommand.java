package view.action;

import controller.BooksController;
import model.Book;
import view.ActionCommand;

import java.util.List;

public class ListAllBooksCommand extends ActionCommand {

  

    @Override
    protected void performAction() throws Exception {
        displayHeader("ALL BOOKS IN LIBRARY");
        
        BooksController controller = BooksController.getInstance();
        List<Book> books = controller.getBooks();
        
        if (books.isEmpty()) {
            System.out.println("📚 No books found in the library.");
            System.out.println("💡 Use 'Add New Book' option to add books to the library.");
            return;
        }
        
        System.out.printf("📖 Total Books: %d%n%n", books.size());
        
        // Table header
        System.out.println("┌─────┬──────────────────────┬──────────────────────┬──────────────┬───────┐");
        System.out.printf("│ %-3s │ %-20s │ %-20s │ %-12s │ %-5s │%n", "No.", "Title", "Author", "ISBN", "Pages");
        System.out.println("├─────┼──────────────────────┼──────────────────────┼──────────────┼───────┤");
        
        // Table rows
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("│ %-3d │ %-20s │ %-20s │ %-12s │ %-5s │%n",
                    (i + 1),
                    truncateString(book.title(), 20),
                    truncateString(book.author(), 20),
                    truncateString(book.isbn(), 12),
                    book.pages());
        }
        
        System.out.println("└─────┴──────────────────────┴──────────────────────┴──────────────┴───────┘");
    }
    
    private String truncateString(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
}
