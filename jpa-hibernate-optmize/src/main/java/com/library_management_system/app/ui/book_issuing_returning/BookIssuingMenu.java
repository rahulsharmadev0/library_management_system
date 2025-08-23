package com.library_management_system.app.ui.book_issuing_returning;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.AppRoute;
import com.library_management_system.app.ui.utils.MenuViewCommand;

public class BookIssuingMenu extends MenuViewCommand {
    private static final Option[] ISSUING_OPTIONS = {
            new Option("Issue Book to Member", "Lend a book to a library member", AppRoute.PlaceHolderCommand),
            new Option("Return Book", "Process book return from member", AppRoute.PlaceHolderCommand),
            new Option("View Issued Books", "See all currently issued books", AppRoute.PlaceHolderCommand),
            new Option("Overdue Books Report", "View books that are overdue", AppRoute.PlaceHolderCommand),
    };

    public BookIssuingMenu() {
        super("📋 BOOK ISSUING & RETURNING", ISSUING_OPTIONS);
    }
}
