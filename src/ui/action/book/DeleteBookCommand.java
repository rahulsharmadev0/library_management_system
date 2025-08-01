package ui.action.book;

import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

public class DeleteBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("Delete Book");

        String id = getInput("Enter book id");

        //  new BookRepository().delete();

        // System.out.println("\n✅ Delete: " + book);

    }
}
