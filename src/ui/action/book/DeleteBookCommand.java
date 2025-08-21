package ui.action.book;

import domain.repositories.BookRepository;
import ui.command.ActionCommand;

public class DeleteBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("Delete Book");

        String id = getInput("Enter book id");
        if (id != null) {
            BookRepository.getInstance().delete(Integer.parseInt(id));

            showMessage("Book with ID " + id + " has been deleted successfully.", false);

            System.out.println("📚 Book deleted successfully.");
        }

    }
}
