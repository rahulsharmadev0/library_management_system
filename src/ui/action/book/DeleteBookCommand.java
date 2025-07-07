package ui.action.book;

import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

public class DeleteBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("Delete Book");

        String index = getInput("Enter book index");

        Book book = BookRepository.instance.remove(Integer.parseInt(index)-1);

        System.out.println("\n✅ Delete: " + book);

    }
}
