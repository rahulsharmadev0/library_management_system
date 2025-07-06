package view;

public class SearchBookMenu extends Menu {
    private static final String[] SEARCH_OPTIONS = {
        "By Title",
        "By Author",
        "By Book ID",
        "Back"
    };

    public SearchBookMenu() {
        super("SEARCH BOOK", SEARCH_OPTIONS);
    }

    @Override
    protected void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                searchByTitle();
                break;
            case 2:
                searchByAuthor();
                break;
            case 3:
                searchById();
                break;
            default:
                System.out.println("❌ Invalid option!");
        }
    }

    private void searchByTitle() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("🔍 Searching for books with title: " + title);
        // TODO: Implement actual search logic
        waitForEnter();
    }

    private void searchByAuthor() {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.println("🔍 Searching for books by author: " + author);
        // TODO: Implement actual search logic
        waitForEnter();
    }

    private void searchById() {
        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();
        System.out.println("🔍 Searching for book with ID: " + id);
        // TODO: Implement actual search logic
        waitForEnter();
    }
}
