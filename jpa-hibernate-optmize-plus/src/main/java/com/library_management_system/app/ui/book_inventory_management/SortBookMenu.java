package com.library_management_system.app.ui.book_inventory_management;
import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.utils.MenuViewCommand;

@Route(title = "Sort Book Menu", 
       description = "Sort books in the library inventory",
       children = {
           SortBookCommand.ByTitle.class,
           SortBookCommand.ByAuthor.class,
           SortBookCommand.ByPages.class
       })
public class SortBookMenu extends MenuViewCommand {
    public SortBookMenu(String[] options) {
        super("📊 SORT BOOKS", options);
    }
}
