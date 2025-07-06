package view;

public class SortBookMenu extends Menu {
    private static final String[] SORT_OPTIONS = {
        "By Title",
        "By Author",
        "By Publication Year",
        "Back"
    };

    public SortBookMenu() {
        super("SORT BOOKS", SORT_OPTIONS);
    }

    @Override
    protected void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                sortByTitle();
                break;
            case 2:
                sortByAuthor();
                break;
            case 3:
                sortByYear();
                break;
            default:
                System.out.println("❌ Invalid option!");
        }
    }

    private void sortByTitle() {
        System.out.println("📚 Sorting books by title...");
        // TODO: Implement actual sorting logic
        waitForEnter();
    }

    private void sortByAuthor() {
        System.out.println("📚 Sorting books by author...");
        // TODO: Implement actual sorting logic
        waitForEnter();
    }

    private void sortByYear() {
        System.out.println("📚 Sorting books by publication year...");
        // TODO: Implement actual sorting logic
        waitForEnter();
    }
}
