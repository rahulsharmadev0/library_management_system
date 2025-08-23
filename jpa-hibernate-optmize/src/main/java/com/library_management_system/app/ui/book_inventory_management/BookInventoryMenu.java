package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.AppRoute;
import com.library_management_system.app.ui.utils.MenuViewCommand;

public class BookInventoryMenu extends MenuViewCommand {
    private static final Option[] BOOK_OPTIONS = {
            new Option("Add New Book", "", AppRoute.AddBookCommand),
            new Option("Delete Book", "", AppRoute.DeleteBookCommand),
            new Option("Update Book Details", "", AppRoute.UpdateBookCommand),
            new Option("Search Book", "", AppRoute.SearchBookMenu),
            new Option("List All Books", "", AppRoute.ListAllBooksCommand),
            new Option("Sort Books", "", AppRoute.SortBookMenu),
    };

    public BookInventoryMenu() {
        super("BOOK INVENTORY MANAGEMENT", BOOK_OPTIONS);
    }

}
