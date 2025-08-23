package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Book;
import com.library_management_system.app.domain.repositories.BookRepository;

public class AddBookCommand extends ActionCommand {

    @Override
    protected void performAction()  {
        displayHeader("ADD NEW BOOK");
        Input.get()
                .takeString("Enter book title")
                .takeString("Enter author name")
                .takeString("Enter ISBN")
                .takeInt("Enter number of pages")
                .execute((inputs) -> {
                    Book newBook = Book.of(
                            inputs[0].toString(),
                            inputs[1].toString(),
                            inputs[2].toString(),
                            (Integer) inputs[3]
                    );
                    BookRepository.getInstance().insert(newBook);
                    showMessage("Book '" + inputs[0].toString() + "' has been successfully added to the library!", false);
                });



    }
}


