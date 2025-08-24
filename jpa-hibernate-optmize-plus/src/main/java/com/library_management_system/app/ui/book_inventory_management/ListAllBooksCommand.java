package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Book;
import com.library_management_system.app.domain.repositories.BookRepository;

import java.util.List;

@Route(title = "List All Books", description = "Display all books in the library inventory")
public class ListAllBooksCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        List<Book> books = BookRepository.getInstance().findAll();
        Book.getTableFormat().setTitle("📚 All Books in Library").display(books);
    }
}
