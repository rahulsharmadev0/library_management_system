package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;

import java.util.List;

import com.library_management_system.app.domain.entities.Book;
import com.library_management_system.app.domain.repositories.BookRepository;


@Route(title = "Update Book Details", description = "Update details of an existing book in the library inventory")
public class UpdateBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("UPDATE BOOK DETAILS");

        Input.get()
                .takeInt("Enter Book ID to update: ")
                .execute((inputs) -> {

                    showMessage("Searching for book...");

                    BookRepository.getInstance().findById((Integer) inputs[0]).ifPresent(
                            book -> {
                                Book.getTableFormat().setTitle("✅ Book Found").display(List.of(book));
                                updateBookDetails(book);
                            }
                    );
                });
    }

    private void updateBookDetails(Book book) {

        Input.get()
                .takeString("Enter new title (leave empty to keep current)")
                .takeString("Enter new author (leave empty to keep current)")
                .takeString("Enter new page count (leave empty to keep current)")
                .execute((inputs) -> {
                    Book updatedBook = new Book(
                            book.getId(),
                            inputs[0].toString().isBlank() ? book.getTitle() : inputs[0].toString(),
                            inputs[1].toString().isBlank() ? book.getAuthor() : inputs[1].toString(),
                            inputs[2].toString().isBlank() ? book.getIsbn() : inputs[2].toString(),
                            inputs[3].toString().isBlank() ? book.getPages() : Integer.parseInt(inputs[3].toString())
                    );
                    BookRepository.getInstance().update(updatedBook);
                    System.out.println("✅ Book updated successfully!");
                });

    }
}
