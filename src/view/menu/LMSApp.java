package view.menu;
import model.Option;
import java.util.HashMap;
import java.util.function.Supplier;

import view.Command;
import view.Menu;
import view.action.AddBookCommand;
import view.action.ListAllBooksCommand;
import view.action.PlaceHolderCommand;

public class LMSApp {
    private HashMap<AppRoute, Supplier<Command>> map = new HashMap<>();

    private LMSApp(){
        map.put(AppRoute.BookIssuingMenu, BookIssuingMenu::new);
        map.put(AppRoute.MainMenu, MainMenu::new);
        map.put(AppRoute.BookInventoryMenu, BookInventoryMenu::new);
        map.put(AppRoute.MemberManagementMenu, MemberManagementMenu::new);
        map.put(AppRoute.SearchBookMenu, SearchBookMenu::new);
        map.put(AppRoute.SearchMemberMenu, SearchMemberMenu::new);
        map.put(AppRoute.SortBookMenu, SortBookMenu::new);
        map.put(AppRoute.PlaceHolderCommand, PlaceHolderCommand::new);
        map.put(AppRoute.ListAllBooksCommand, ListAllBooksCommand::new);
        map.put(AppRoute.AddBookCommand, AddBookCommand::new);
    }

    static LMSApp _instance = new LMSApp();

    public static  Command getMenu(AppRoute menu){
        return _instance.map.get(menu).get();
    }
    
}



 class MainMenu extends Menu{
    private static final Option[] MAIN_OPTIONS = {
            new Option("Book Inventory Management", "", AppRoute.BookInventoryMenu),
            new Option("Member Management", "", AppRoute.MemberManagementMenu),
            new Option("Book Issuing & Returning", "", AppRoute.BookIssuingMenu),
    };

    public MainMenu() {
        super("LIBRARY MANAGEMENT SYSTEM", MAIN_OPTIONS);
    }
}



class BookInventoryMenu extends Menu {
 private static final Option[] BOOK_OPTIONS = {
     new Option("Add New Book", "", AppRoute.AddBookCommand),
     new Option("Delete Book", "", AppRoute.PlaceHolderCommand),
     new Option("Update Book Details", "", AppRoute.PlaceHolderCommand),
     new Option("Search Book", "", AppRoute.PlaceHolderCommand),
     new Option("List All Books", "", AppRoute.ListAllBooksCommand),
     new Option("Sort Books", "", AppRoute.PlaceHolderCommand),
 };

    public BookInventoryMenu() {
        super("BOOK INVENTORY MANAGEMENT", BOOK_OPTIONS);
    }

}

class BookIssuingMenu extends Menu {
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


class MemberManagementMenu extends Menu {
    private static final Option[] MEMBER_OPTIONS = {
            new Option("Register New Member", "", AppRoute.PlaceHolderCommand),
            new Option("Update Member Info", "", AppRoute.PlaceHolderCommand),
            new Option("Delete Member", "", AppRoute.PlaceHolderCommand),
            new Option("Search Member", "", AppRoute.PlaceHolderCommand),
            new Option("List All Members", "", AppRoute.PlaceHolderCommand),
            new Option("Go Back", "", AppRoute.PlaceHolderCommand)
    };

    public MemberManagementMenu() {
        super("MEMBER MANAGEMENT", MEMBER_OPTIONS);
    }

}

class SortBookMenu extends Menu {
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


class SearchMemberMenu extends Menu {
    private static final Option[] SEARCH_OPTIONS = {
            new Option("Search by Member ID", "Find member by ID number", AppRoute.PlaceHolderCommand),
            new Option("Search by Name", "Find members by name", AppRoute.PlaceHolderCommand),
            new Option("Search by Email", "Find member by email address", AppRoute.PlaceHolderCommand),
            new Option("Search by Phone", "Find member by phone number", AppRoute.PlaceHolderCommand),
    };

    public SearchMemberMenu() {
        super("🔍 SEARCH MEMBERS", SEARCH_OPTIONS);
    }
}


class SearchBookMenu extends Menu {
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
