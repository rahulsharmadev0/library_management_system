package view;

public class BookIssuingMenu extends Menu {
    private static final String[] ISSUING_OPTIONS = {
        "Issue Book to Member",
        "Return Book",
        "View Issued Books",
        "Go Back"
    };

    public BookIssuingMenu() {
        super("ISSUE / RETURN BOOK", ISSUING_OPTIONS);
    }

    @Override
    protected void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                issueBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                viewIssuedBooks();
                break;
            default:
                System.out.println("❌ Invalid option!");
        }
    }

    private void issueBook() {
        System.out.println("\n========= ISSUE BOOK =========");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        // TODO: Implement book issuing logic
        // - Validate member exists
        // - Check if book is available
        // - Create issue record
        System.out.println("✅ Book issued successfully!");
        waitForEnter();
    }

    private void returnBook() {
        System.out.println("\n========= RETURN BOOK =========");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        // TODO: Implement book return logic
        // - Validate issue record exists
        // - Calculate any fines
        // - Update book status
        System.out.println("✅ Book returned successfully!");
        waitForEnter();
    }

    private void viewIssuedBooks() {
        System.out.println("\n========= ISSUED BOOKS =========");
        // TODO: Implement viewing issued books
        // - Show all currently issued books
        // - Include member details and due dates
        System.out.println("Coming soon!");
        waitForEnter();
    }
}
