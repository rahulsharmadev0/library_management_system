package routes;

import model.Option;
import java.util.Map;
import java.util.function.Supplier;
import action.AddBookCommand;
import action.ListAllBooksCommand;
import action.PlaceHolderCommand;
import command.Command;
import command.MenuViewCommand;

public class AppRoute {
    private static final Map<AppRouteName, Supplier<Command>> ROUTE_MAP = Map.of(
            AppRouteName.BookIssuingMenu, BookIssuingMenu::new,
            AppRouteName.MainMenu, MainMenu::new,
            AppRouteName.BookInventoryMenu, BookInventoryMenu::new,
            AppRouteName.MemberManagementMenu, MemberManagementMenu::new,
            AppRouteName.SearchBookMenu, SearchBookMenu::new,
            AppRouteName.SearchMemberMenu, SearchMemberMenu::new,
            AppRouteName.SortBookMenu, SortBookMenu::new,
            AppRouteName.PlaceHolderCommand, PlaceHolderCommand::new,
            AppRouteName.ListAllBooksCommand, ListAllBooksCommand::new,
            AppRouteName.AddBookCommand, AddBookCommand::new

    );


    public static Command get(AppRouteName menu) {
        return ROUTE_MAP.get(menu).get();
    }

}

class MainMenu extends MenuViewCommand {
    private static final Option[] MAIN_OPTIONS = {
            new Option("Book Inventory Management", "", AppRouteName.BookInventoryMenu),
            new Option("Member Management", "", AppRouteName.MemberManagementMenu),
            new Option("Book Issuing & Returning", "", AppRouteName.BookIssuingMenu),
    };

    public MainMenu() {
        super("LIBRARY MANAGEMENT SYSTEM", MAIN_OPTIONS);
    }
}

class BookInventoryMenu extends MenuViewCommand {
    private static final Option[] BOOK_OPTIONS = {
            new Option("Add New Book", "", AppRouteName.AddBookCommand),
            new Option("Delete Book", "", AppRouteName.PlaceHolderCommand),
            new Option("Update Book Details", "", AppRouteName.PlaceHolderCommand),
            new Option("Search Book", "", AppRouteName.PlaceHolderCommand),
            new Option("List All Books", "", AppRouteName.ListAllBooksCommand),
            new Option("Sort Books", "", AppRouteName.PlaceHolderCommand),
    };

    public BookInventoryMenu() {
        super("BOOK INVENTORY MANAGEMENT", BOOK_OPTIONS);
    }

}

class BookIssuingMenu extends MenuViewCommand {
    private static final Option[] ISSUING_OPTIONS = {
            new Option("Issue Book to Member", "Lend a book to a library member", AppRouteName.PlaceHolderCommand),
            new Option("Return Book", "Process book return from member", AppRouteName.PlaceHolderCommand),
            new Option("View Issued Books", "See all currently issued books", AppRouteName.PlaceHolderCommand),
            new Option("Overdue Books Report", "View books that are overdue", AppRouteName.PlaceHolderCommand),
    };

    public BookIssuingMenu() {
        super("📋 BOOK ISSUING & RETURNING", ISSUING_OPTIONS);
    }
}

class MemberManagementMenu extends MenuViewCommand {
    private static final Option[] MEMBER_OPTIONS = {
            new Option("Register New Member", "", AppRouteName.PlaceHolderCommand),
            new Option("Update Member Info", "", AppRouteName.PlaceHolderCommand),
            new Option("Delete Member", "", AppRouteName.PlaceHolderCommand),
            new Option("Search Member", "", AppRouteName.PlaceHolderCommand),
            new Option("List All Members", "", AppRouteName.PlaceHolderCommand),
            new Option("Go Back", "", AppRouteName.PlaceHolderCommand)
    };

    public MemberManagementMenu() {
        super("MEMBER MANAGEMENT", MEMBER_OPTIONS);
    }

}

class SortBookMenu extends MenuViewCommand {
    private static final Option[] SORT_OPTIONS = {
            new Option("Sort by Title (A-Z)", "Sort books alphabetically by title", AppRouteName.PlaceHolderCommand),
            new Option("Sort by Author (A-Z)", "Sort books alphabetically by author", AppRouteName.PlaceHolderCommand),
            new Option("Sort by ISBN", "Sort books by ISBN number", AppRouteName.PlaceHolderCommand),
            new Option("Sort by Pages", "Sort books by number of pages", AppRouteName.PlaceHolderCommand),
    };

    public SortBookMenu() {
        super("📊 SORT BOOKS", SORT_OPTIONS);
    }
}

class SearchMemberMenu extends MenuViewCommand {
    private static final Option[] SEARCH_OPTIONS = {
            new Option("Search by Member ID", "Find member by ID number", AppRouteName.PlaceHolderCommand),
            new Option("Search by Name", "Find members by name", AppRouteName.PlaceHolderCommand),
            new Option("Search by Email", "Find member by email address", AppRouteName.PlaceHolderCommand),
            new Option("Search by Phone", "Find member by phone number", AppRouteName.PlaceHolderCommand),
    };

    public SearchMemberMenu() {
        super("🔍 SEARCH MEMBERS", SEARCH_OPTIONS);
    }
}

class SearchBookMenu extends MenuViewCommand {
    private static final Option[] SEARCH_OPTIONS = {
            new Option("Search by Title", "Find books by title", AppRouteName.PlaceHolderCommand),
            new Option("Search by Author", "Find books by author name", AppRouteName.PlaceHolderCommand),
            new Option("Search by ISBN", "Find books by ISBN number", AppRouteName.PlaceHolderCommand),
            new Option("Advanced Search", "Search by multiple criteria", AppRouteName.PlaceHolderCommand),
    };

    public SearchBookMenu() {
        super("🔍 SEARCH BOOKS", SEARCH_OPTIONS);
    }
}
