package action.book;

import command.ActionCommand;
import model.Book;
import service.BookService;

public class DeleteBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("Delete Book");

        String index = getInput("Enter book index");

        Book book = BookService.instance.removeBook(Integer.parseInt(index)-1);

        System.out.println("\n✅ Delete: " + book);

    }
}
