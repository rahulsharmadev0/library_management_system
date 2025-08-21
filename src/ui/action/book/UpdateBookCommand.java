package ui.action.book;

import ui.command.ActionCommand;
import java.util.List;
import domain.entities.Book;
import domain.repositories.BookRepository;

public class UpdateBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("UPDATE BOOK DETAILS");

        String idStr = getInput("Enter Book ID to update: ");

        if (idStr == null || idStr.trim().isEmpty()) {
            showMessage("ISBN cannot be empty. Operation cancelled.", true);
            return;
        }

        System.out.println("Searching for book...");
        try {

            BookRepository.getInstance().findById(Integer.parseInt(idStr)).ifPresent(
                    book -> {
                        Book.getTableFormat().setTitle("✅ Book Found").display(List.of(book));
                        try {
                            updateBookDetails(book);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format. Please enter a valid number.");
        }
    }

    private void updateBookDetails(Book book) throws Exception {
        String title = getInput("Enter new title (leave empty to keep current): ");
        String author = getInput("Enter new author (leave empty to keep current): ");
        String page = getInput("Enter new page count (leave empty to keep current): ");

        Book updatedBook = new Book(
                book.id(),
                !title.trim().isEmpty() ? title : book.title(),
                !author.trim().isEmpty() ? author : book.author(),
                book.isbn(),
                !page.trim().isEmpty() ? Integer.parseInt(page) : book.pages());

        BookRepository.getInstance().update(updatedBook);
        System.out.println("✅ Book updated successfully!");
    }
}
