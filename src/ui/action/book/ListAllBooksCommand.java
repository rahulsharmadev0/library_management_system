package ui.action.book;

import utils.TableFormatter;
import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

import java.util.List;

public class ListAllBooksCommand extends ActionCommand {

    private static final TableFormatter<Book> BOOK_TABLE = 
        TableFormatter.<Book>builder("ALL BOOKS IN LIBRARY")
            .withStyle(TableFormatter.Style.LIGHT)
            .addColumn("Title", 20, Book::title)
            .addColumn("Author", 20, Book::author)
            .addColumn("ISBN", 14, Book::isbn)
            .addColumn("Pages", 6, Book::pages, TableFormatter.Alignment.RIGHT);

    @Override
    protected void performAction() throws Exception {
        displayHeader("ALL BOOKS IN LIBRARY");
        
        List<Book> books = BookRepository.getInstance().getAll();
        
        if (books.isEmpty()) {
            System.out.println("📚 No books found in the library.");
            System.out.println("💡 Use 'Add New Book' option to add books to the library.");
            return;
        }
        
        BOOK_TABLE.display(books);
    }
}
