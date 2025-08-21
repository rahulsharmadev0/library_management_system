package ui.action.book;

import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

public class AddBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("ADD NEW BOOK");

        String title = getInput("Enter book title", true);
        String author = getInput("Enter author name", true);
        String isbn = getInput("Enter ISBN", true);
        String pages = getInput("Enter number of pages", true);


        Book newBook = new Book(0, title, author, isbn, Integer.parseInt(pages));
        BookRepository.getInstance().insert(newBook);
        showMessage("Book '" + title + "' has been successfully added to the library!", false);
    }
}
