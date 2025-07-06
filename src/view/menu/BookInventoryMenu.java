package view;

public class BookInventoryMenu extends Menu {
    private static final String[] BOOK_OPTIONS = {
        "Add New Book",
        "Delete Book",
        "Update Book Details",
        "Search Book",
        "List All Books",
        "Sort Books",
        "Go Back"
    };

    public BookInventoryMenu() {
        super("BOOK INVENTORY MANAGEMENT", BOOK_OPTIONS);
        initializeCommands();
    }

    private void initializeCommands() {
        menuInvoker.setCommand("add_book", new AddBookCommand());
        menuInvoker.setCommand("search_book", new SearchBookMenu());
        menuInvoker.setCommand("sort_book", new SortBookMenu());
    }

    @Override
    protected void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                menuInvoker.executeCommand("add_book");
                break;
            case 2:
                System.out.println("📚 Delete Book - Coming soon!");
                waitForEnter();
                break;
            case 3:
                System.out.println("📚 Update Book - Coming soon!");
                waitForEnter();
                break;
            case 4:
                menuInvoker.executeCommand("search_book");
                break;
            case 5:
                System.out.println("📚 List All Books - Coming soon!");
                waitForEnter();
                break;
            case 6:
                menuInvoker.executeCommand("sort_book");
                break;
            default:
                System.out.println("❌ Invalid option!");
        }
    }
}
