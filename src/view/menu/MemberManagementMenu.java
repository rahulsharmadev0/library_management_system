package view;

public class MemberManagementMenu extends Menu {
    private static final String[] MEMBER_OPTIONS = {
        "Register New Member",
        "Update Member Info",
        "Delete Member",
        "Search Member",
        "List All Members",
        "Go Back"
    };

    public MemberManagementMenu() {
        super("MEMBER MANAGEMENT", MEMBER_OPTIONS);
        initializeCommands();
    }

    private void initializeCommands() {
        menuInvoker.setCommand("search_member", new SearchMemberMenu());
    }

    @Override
    protected void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                registerNewMember();
                break;
            case 2:
                updateMember();
                break;
            case 3:
                deleteMember();
                break;
            case 4:
                menuInvoker.executeCommand("search_member");
                break;
            case 5:
                listAllMembers();
                break;
            default:
                System.out.println("❌ Invalid option!");
        }
    }

    private void registerNewMember() {
        System.out.println("\n========= REGISTER NEW MEMBER =========");
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        // TODO: Implement actual member registration logic
        System.out.println("✅ Member registered successfully!");
        waitForEnter();
    }

    private void updateMember() {
        System.out.println("\n========= UPDATE MEMBER INFO =========");
        System.out.print("Enter Member ID to update: ");
        String id = scanner.nextLine();
        // TODO: Implement member update logic
        System.out.println("Coming soon!");
        waitForEnter();
    }

    private void deleteMember() {
        System.out.println("\n========= DELETE MEMBER =========");
        System.out.print("Enter Member ID to delete: ");
        String id = scanner.nextLine();
        System.out.print("Are you sure you want to delete this member? (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            // TODO: Implement member deletion logic
            System.out.println("✅ Member deleted successfully!");
        } else {
            System.out.println("Operation cancelled.");
        }
        waitForEnter();
    }

    private void listAllMembers() {
        System.out.println("\n========= ALL MEMBERS =========");
        // TODO: Implement listing all members
        System.out.println("Coming soon!");
        waitForEnter();
    }
}
