package com.library_management_system.app.ui.book_inventory_management;
import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.utils.MenuViewCommand;

@Route(title = "Search Book Menu", 
       description = "Search for books in the library inventory",
       children = {
           SearchBookCommand.ByTitle.class,
           SearchBookCommand.ByAuthor.class,
           SearchBookCommand.ByISBN.class
       })
public class SearchBookMenuCommand extends MenuViewCommand {
    public SearchBookMenuCommand(String[] options) {
        super("🔍 SEARCH BOOKS", options);
    }
}
