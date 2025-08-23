package ui.action.book;

import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

import java.util.Optional;

public class UpdateBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("UPDATE BOOK DETAILS");

        String isbn = getInput("Enter Book ISBN to update: ");
        
        if (isbn == null || isbn.trim().isEmpty()) {
            showMessage("ISBN cannot be empty. Operation cancelled.",true);
            return;
        }

        System.out.println("Searching for book...");
        Optional<Book> bookOptional = BookRepository.instance.findBy(s -> s.isbn().equals(isbn));
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            displayBookDetails(book);
            updateBookDetails(book);
        } else {
            System.out.println("❌ Book not found with ISBN: " + isbn);
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

    private void updateBookDetails(Book book) throws Exception {
        String title = getInput("Enter new title (leave empty to keep current): ");
        String author = getInput("Enter new author (leave empty to keep current): ");
        String page = getInput("Enter new page count (leave empty to keep current): ");

        Book updatedBook = new Book(
            !title.trim().isEmpty() ? title : book.title(),
            !author.trim().isEmpty() ? author : book.author(),
            book.isbn(),
            !page.trim().isEmpty() ? page : book.pages()
        );

        BookRepository.instance.update(updatedBook, (s)->s.isbn().equals(updatedBook.isbn()));
        System.out.println("✅ Book updated successfully!");
    }
}
