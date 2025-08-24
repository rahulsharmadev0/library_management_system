package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.domain.repositories.BookRepository;
import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;

@Route(title = "Delete Book", description = "Delete a book from the library inventory")
public class DeleteBookCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("Delete Book");
        Input.get()
                .takeInt("Enter book id")
                .execute(inputs -> {
                    BookRepository.getInstance().delete((Integer) inputs[0]);
                    showMessage("Book with ID " + inputs[0] + " has been deleted successfully.", false);
                });

    }
}
