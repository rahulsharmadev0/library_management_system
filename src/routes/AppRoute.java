package routes;

import model.Option;
import java.util.function.Supplier;
import action.book.*;
import action.member.*;
import command.Command;
import command.MenuViewCommand;

public enum AppRoute {
    BookIssuingMenu(BookIssuingMenu::new),
    MainMenu(MainMenu::new),
    BookInventoryMenu(BookInventoryMenu::new),
    MemberManagementMenu(MemberManagementMenu::new),
    SearchBookMenu(SearchBookMenu::new),
    SearchMemberMenu(SearchMemberMenu::new),
    SortBookMenu(SortBookMenu::new),
    PlaceHolderCommand(PlaceHolderCommand::new),
    AddBookCommand(AddBookCommand::new),
    ListAllBooksCommand(ListAllBooksCommand::new),
    DeleteBookCommand(DeleteBookCommand::new),
    UpdateBookCommand(UpdateBookCommand::new),
    RegisterNewMemberCommand(RegisterNewMemberCommand::new),
    ListAllMembersCommand(ListAllMembersCommand::new),
    UpdateMemberCommand(UpdateMemberCommand::new),
    DeleteMemberCommand(DeleteMemberCommand::new),
    SearchMemberCommandByEmail(SearchMemberCommand.ByEmail::new),
    SearchMemberCommandById(SearchMemberCommand.ById::new),
    SearchMemberCommandByName(SearchMemberCommand.ByName::new),
    SearchMemberCommandByPhone(SearchMemberCommand.ByPhone::new);

    protected final Supplier<Command> comSupplier;

    AppRoute(Supplier<Command> comSupplier){
        this.comSupplier = comSupplier;
    }

    public Command get() {
        return this.comSupplier.get();
    }

}

class MainMenu extends MenuViewCommand {
    private static final Option[] MAIN_OPTIONS = {
            new Option("Book Inventory Management", "", AppRoute.BookInventoryMenu),
            new Option("Member Management", "", AppRoute.MemberManagementMenu),
            new Option("Book Issuing & Returning", "", AppRoute.BookIssuingMenu),
    };

    public MainMenu() {
        super("LIBRARY MANAGEMENT SYSTEM", MAIN_OPTIONS);
    }
}

class BookInventoryMenu extends MenuViewCommand {
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

class BookIssuingMenu extends MenuViewCommand {
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

class MemberManagementMenu extends MenuViewCommand {
    private static final Option[] MEMBER_OPTIONS = {
            new Option("Register New Member", "", AppRoute.RegisterNewMemberCommand),
            new Option("Update Member Info", "", AppRoute.UpdateMemberCommand),
            new Option("Delete Member", "", AppRoute.DeleteMemberCommand),
            new Option("Search Member", "", AppRoute.SearchMemberMenu),
            new Option("List All Members", "", AppRoute.ListAllMembersCommand),
    };

    public MemberManagementMenu() {
        super("MEMBER MANAGEMENT", MEMBER_OPTIONS);
    }

}

class SortBookMenu extends MenuViewCommand {
    private static final Option[] SORT_OPTIONS = {
            new Option("Sort by Title (A-Z)", "Sort books alphabetically by title", AppRoute.PlaceHolderCommand),
            new Option("Sort by Author (A-Z)", "Sort books alphabetically by author", AppRoute.PlaceHolderCommand),
            new Option("Sort by ISBN", "Sort books by ISBN number", AppRoute.PlaceHolderCommand),
            new Option("Sort by Pages", "Sort books by number of pages", AppRoute.PlaceHolderCommand),
    };

    public SortBookMenu() {
        super("📊 SORT BOOKS", SORT_OPTIONS);
    }
}

class SearchMemberMenu extends MenuViewCommand {
    private static final Option[] SEARCH_OPTIONS = {
            new Option("Search by Member ID", "Find member by ID number", AppRoute.SearchMemberCommandById),
            new Option("Search by Name", "Find members by name", AppRoute.SearchMemberCommandByName),
            new Option("Search by Email", "Find member by email address", AppRoute.SearchMemberCommandByEmail),
            new Option("Search by Phone", "Find member by phone number", AppRoute.SearchMemberCommandByPhone),
    };

    public SearchMemberMenu() {
        super("🔍 SEARCH MEMBERS", SEARCH_OPTIONS);
    }
}

class SearchBookMenu extends MenuViewCommand {
    private static final Option[] SEARCH_OPTIONS = {
            new Option("Search by Title", "Find books by title", AppRoute.PlaceHolderCommand),
            new Option("Search by Author", "Find books by author name", AppRoute.PlaceHolderCommand),
            new Option("Search by ISBN", "Find books by ISBN number", AppRoute.PlaceHolderCommand),
            new Option("Advanced Search", "Search by multiple criteria", AppRoute.PlaceHolderCommand),
    };

    public SearchBookMenu() {
        super("🔍 SEARCH BOOKS", SEARCH_OPTIONS);
    }
}
