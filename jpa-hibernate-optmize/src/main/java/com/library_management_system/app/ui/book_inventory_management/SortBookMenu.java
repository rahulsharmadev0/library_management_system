package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.AppRoute;
import com.library_management_system.app.ui.utils.MenuViewCommand;

public class SortBookMenu extends MenuViewCommand {
    private static final Option[] SORT_OPTIONS = {
            new Option("Sort by Title (A-Z)", "Sort books alphabetically by title", AppRoute.SortBookCommandByTitle),
            new Option("Sort by Author (A-Z)", "Sort books alphabetically by author", AppRoute.SortBookCommandByAuthor),
            new Option("Sort by Pages", "Sort books by number of pages", AppRoute.SortBookCommandByPages),
    };

    public SortBookMenu() {
        super("📊 SORT BOOKS", SORT_OPTIONS);
    }
}
