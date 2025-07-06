package routes;

import model.Option;
import java.util.Map;
import java.util.function.Supplier;
import action.book.AddBookCommand;
import action.book.DeleteBookCommand;
import action.book.ListAllBooksCommand;
import action.book.PlaceHolderCommand;
import action.book.UpdateBookCommand;
import action.member.RegisterNewMemberCommand;
import action.member.ListAllMembersCommand;
import action.member.UpdateMemberCommand;
import action.member.DeleteMemberCommand;
import action.member.SearchMemberCommand;
import command.Command;
import command.MenuViewCommand;

public class AppRoute {
    private static final Map<AppRouteName, Supplier<Command>> ROUTE_MAP = Map.ofEntries(
            Map.entry(AppRouteName.BookIssuingMenu, BookIssuingMenu::new),
            Map.entry(AppRouteName.MainMenu, MainMenu::new),
            Map.entry(AppRouteName.BookInventoryMenu, BookInventoryMenu::new),
            Map.entry(AppRouteName.MemberManagementMenu, MemberManagementMenu::new),
            Map.entry(AppRouteName.SearchBookMenu, SearchBookMenu::new),
            Map.entry(AppRouteName.SearchMemberMenu, SearchMemberMenu::new),
            Map.entry(AppRouteName.SortBookMenu, SortBookMenu::new),
            Map.entry(AppRouteName.PlaceHolderCommand, PlaceHolderCommand::new),
            Map.entry(AppRouteName.ListAllBooksCommand, ListAllBooksCommand::new),
            Map.entry(AppRouteName.AddBookCommand, AddBookCommand::new),
            Map.entry(AppRouteName.DeleteBookCommand, DeleteBookCommand::new),
            Map.entry(AppRouteName.UpdateBookCommand, UpdateBookCommand::new),
            Map.entry(AppRouteName.RegisterNewMemberCommand, RegisterNewMemberCommand::new),
            Map.entry(AppRouteName.ListAllMembersCommand, ListAllMembersCommand::new),
            Map.entry(AppRouteName.UpdateMemberCommand, UpdateMemberCommand::new),
            Map.entry(AppRouteName.DeleteMemberCommand, DeleteMemberCommand::new),

            Map.entry(AppRouteName.SearchMemberCommandByEmail, SearchMemberCommand.ByEmail::new),
            Map.entry(AppRouteName.SearchMemberCommandById, SearchMemberCommand.ById::new),
            Map.entry(AppRouteName.SearchMemberCommandByName, SearchMemberCommand.ByName::new),
            Map.entry(AppRouteName.SearchMemberCommandByPhone, SearchMemberCommand.ByPhone::new)

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
            new Option("Delete Book", "", AppRouteName.DeleteBookCommand),
            new Option("Update Book Details", "", AppRouteName.UpdateBookCommand),
            new Option("Search Book", "", AppRouteName.SearchBookMenu),
            new Option("List All Books", "", AppRouteName.ListAllBooksCommand),
            new Option("Sort Books", "", AppRouteName.SortBookMenu),
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
            new Option("Register New Member", "", AppRouteName.RegisterNewMemberCommand),
            new Option("Update Member Info", "", AppRouteName.UpdateMemberCommand),
            new Option("Delete Member", "", AppRouteName.DeleteMemberCommand),
            new Option("Search Member", "", AppRouteName.SearchMemberMenu),
            new Option("List All Members", "", AppRouteName.ListAllMembersCommand),
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
            new Option("Search by Member ID", "Find member by ID number", AppRouteName.SearchMemberCommandById),
            new Option("Search by Name", "Find members by name", AppRouteName.SearchMemberCommandByName),
            new Option("Search by Email", "Find member by email address", AppRouteName.SearchMemberCommandByEmail),
            new Option("Search by Phone", "Find member by phone number", AppRouteName.SearchMemberCommandByPhone),
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
