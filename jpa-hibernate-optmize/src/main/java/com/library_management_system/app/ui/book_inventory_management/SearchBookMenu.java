package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.AppRoute;
import com.library_management_system.app.ui.utils.MenuViewCommand;

public class SearchBookMenu extends MenuViewCommand {
    private static final Option[] SEARCH_OPTIONS = {
            new Option("Search by Title", "Find books by title", AppRoute.SearchBookCommandByTitle),
            new Option("Search by Author", "Find books by author name", AppRoute.SearchBookCommandByAuthor),
            new Option("Search by ISBN", "Find books by ISBN number", AppRoute.SearchBookCommandByISBN),
    };

    public SearchBookMenu() {
        super("🔍 SEARCH BOOKS", SEARCH_OPTIONS);
    }
}
