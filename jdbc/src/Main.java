import ui.AppRoute;
import ui.Navigator;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("📚 Welcome to Library Management System");
            System.out.println("═".repeat(50));

            Navigator.initialize();
            Navigator navigator = Navigator.getNavigator();
            navigator.start(AppRoute.MainMenu);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
