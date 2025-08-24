package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.utils.MenuViewCommand;

@Route(title = "Book Inventory Management", description = "Manage the library's book inventory", children = {
        ListAllBooksCommand.class,
        AddBookCommand.class,
        UpdateBookCommand.class,
        DeleteBookCommand.class,
        SearchBookMenuCommand.class,
        SortBookMenu.class,
})
public class BookInventoryMenuCommand extends MenuViewCommand {
    public BookInventoryMenuCommand(String[] options) {
        super("BOOK INVENTORY MANAGEMENT", options);
    }
}
