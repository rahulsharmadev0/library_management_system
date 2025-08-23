package ui.command;

import domain.entities.Option;
import ui.AppRoute;
import ui.Navigator;

import java.util.Scanner;

public abstract class MenuViewCommand extends ActionCommand {
    protected final Scanner scanner;
    protected final String title;
    protected final Option[] options;

    public MenuViewCommand(String title, Option[] options) {
        this.title = title;
        this.options = options;
        this.scanner = new Scanner(System.in);
    }

    protected void showMessage(String message, boolean isError) {
        String icon = isError ? "❌" : "✅";
        System.out.println("\n" + icon + " " + message);
        waitForEnter();
    }

    @Override
    public void performAction() throws Exception {
        displayHeader(title);
        displayMenuOptions();

        try {
            String input = super.getInput();

            if (input.isEmpty()) {
                showMessage("Please enter a valid choice", true);
                return;
            }

            int choice = Integer.parseInt(input);
            Navigator navigator = Navigator.getNavigator();

            // Calculate total options including navigation
            int totalOptions = options.length + (navigator.isAtRoot() ? 1 : 2);

            if (choice < 1 || choice > totalOptions) {
                showMessage("Invalid choice. Please select between 1 and " + totalOptions, true);
                return;
            }

            

            // Handle regular menu options
            if (choice <= options.length) {
                AppRoute selectedCommand = options[choice - 1].routeName();

                // If it's a menu, navigate to it; if it's an action, execute it and stay in
                // current menu
                if (selectedCommand.get() instanceof MenuViewCommand) {
                    navigator.navigateTo(selectedCommand);
                } else {
                    // TODO: Here it call Command 
                    selectedCommand.get().execute();
                    // After action execution, re-execute current menu
                    execute();
                }
                return;
            }

            // Handle navigation options
            choice -= options.length;

            if (!navigator.isAtRoot() && choice == 1) {
                // Go Back
                navigator.goBack();
            } else if ((navigator.isAtRoot() && choice == 1) || (!navigator.isAtRoot() && choice == 2)) {
                // Exit
                navigator.exit();
            }

        } catch (NumberFormatException e) {
            showMessage("Please enter a valid number", true);
            execute(); // Re-show menu
        } catch (Exception e) {
            showMessage("An error occurred: " + e.getMessage(), true);
            execute(); // Re-show menu
        }
    }

    private void displayMenuOptions() {
        int i = 0;
        while (i < options.length)
            System.out.println(i + 1 + ". " + options[i++].title());

        if (!Navigator.getNavigator().isAtRoot()) {
            System.out.println(++i + "🔙 Go Back");

            System.out.println(++i + "🚪 Exit");
        }
    }
}
