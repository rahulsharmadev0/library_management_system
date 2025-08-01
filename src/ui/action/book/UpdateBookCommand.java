package ui.action.book;

import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

import java.util.Optional;

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
            BookRepository repository = BookRepository.getInstance();
            Book book = repository.findById(Integer.parseInt(idStr));
            if (book != null) {
                displayBookDetails(book);
                updateBookDetails(book, repository);
            } else {
                System.out.println("❌ Book not found with ID: " + idStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format. Please enter a valid number.");
        }
    }

    private void displayBookDetails(Book book) {
        System.out.println("✅ Book Found:");
        System.out.println("-----------------------------------------");
        System.out.println("ISBN    : " + book.isbn());
        System.out.println("Title   : " + book.title());
        System.out.println("Author  : " + book.author());
        System.out.println("Pages: " + book.pages());
        System.out.println("-----------------------------------------");
    }

    private void updateBookDetails(Book book, BookRepository repository) throws Exception {
        String title = getInput("Enter new title (leave empty to keep current): ");
        String author = getInput("Enter new author (leave empty to keep current): ");
        String page = getInput("Enter new page count (leave empty to keep current): ");

        Book updatedBook = new Book(
                book.id(),
                !title.trim().isEmpty() ? title : book.title(),
                !author.trim().isEmpty() ? author : book.author(),
                book.isbn(),
                !page.trim().isEmpty() ? page : book.pages());

        repository.update(updatedBook);
        System.out.println("✅ Book updated successfully!");
    }
}
