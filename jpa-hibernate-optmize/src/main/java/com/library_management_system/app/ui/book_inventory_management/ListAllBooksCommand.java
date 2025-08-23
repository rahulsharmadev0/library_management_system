package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Book;
import com.library_management_system.app.domain.repositories.BookRepository;

import java.util.List;

public class ListAllBooksCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        List<Book> books = BookRepository.getInstance().findAll();
        Book.getTableFormat().setTitle("📚 All Books in Library").display(books);
    }
}
