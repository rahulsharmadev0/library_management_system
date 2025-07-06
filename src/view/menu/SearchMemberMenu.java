package view;

public class SearchMemberMenu extends Menu {
    private static final String[] SEARCH_OPTIONS = {
        "By Member ID",
        "By Name",
        "Back"
    };

    public SearchMemberMenu() {
        super("SEARCH MEMBER", SEARCH_OPTIONS);
    }

    @Override
    protected void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                searchById();
                break;
            case 2:
                searchByName();
                break;
            default:
                System.out.println("❌ Invalid option!");
        }
    }

    private void searchById() {
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.println("🔍 Searching for member with ID: " + id);
        // TODO: Implement actual search logic
        waitForEnter();
    }

    private void searchByName() {
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();
        System.out.println("🔍 Searching for members with name: " + name);
        // TODO: Implement actual search logic
        waitForEnter();
    }
}
