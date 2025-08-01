package ui.action.book;

import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

public class AddBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("ADD NEW BOOK");

        String title = getInput("Enter book title");
        if (title.isEmpty()) {
            showMessage("Title cannot be empty", true);
            return;
        }
        
        String author = getInput("Enter author name");
        if (author.isEmpty()) {
            showMessage("Author name cannot be empty", true);
            return;
        }
        
        String isbn = getInput("Enter ISBN");
        if (isbn.isEmpty()) {
            showMessage("ISBN cannot be empty", true);
            return;
        }
        
        String pages = getInput("Enter number of pages");
        if (pages.isEmpty()) {
            showMessage("Number of pages cannot be empty", true);
            return;
        }
        
        // Validate pages is a number
        try {
            Integer.parseInt(pages);
        } catch (NumberFormatException e) {
            showMessage("Number of pages must be a valid number", true);
            return;
        }
        
        Book newBook = new Book(0, title, author, isbn, pages);
        BookRepository.getInstance().insert(newBook);
        showMessage("Book '" + title + "' has been successfully added to the library!", false);
    }
}
