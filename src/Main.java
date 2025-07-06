import controller.Navigator;
import view.menu.LMSApp;
import view.menu.AppRoute;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("📚 Welcome to Library Management System");
            System.out.println("═".repeat(50));

            Navigator.initialize();
            
            Navigator navigator = Navigator.getNavigator();

            navigator.start(LMSApp.getMenu(AppRoute.MainMenu));

        } catch (Exception e) {
            System.err.println("❌ Failed to start application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}