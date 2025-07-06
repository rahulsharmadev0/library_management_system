package controller;

import java.util.Stack;

import command.Command;

public class Navigator {
    private Stack<Command> navigationStack = new Stack<>();
    private static Navigator navigator = null;
    private boolean isRunning = true;

    public static Navigator getNavigator() {
        return navigator;
    }

    public static void initialize() {
        navigator = new Navigator();
    }

    private Navigator() {}

    /**
     * Navigate to a new command/menu
     */
    public void navigateTo(Command command) {
        navigationStack.push(command);
    }

    /**
     * Go back to the previous menu
     */
    public void goBack() {
        if (!navigationStack.isEmpty()) {
            navigationStack.pop();
        }
        
        // If stack is empty, we're at the root - should exit
        if (navigationStack.isEmpty()) {
            exit();
        }
    }

    /**
     * Get the current command without removing it from stack
     */
    public Command getCurrentCommand() {
        if (navigationStack.isEmpty()) {
            return null;
        }
        return navigationStack.peek();
    }

    /**
     * Check if we're at the root level
     */
    public boolean isAtRoot() {
        return navigationStack.size() <= 1;
    }

    /**
     * Exit the application
     */
    public void exit() {
        isRunning = false;
        System.out.println("\n🔃 Exiting Library Management System...");
        System.out.println("👋 Thank you for using our system!");
    }

    /**
     * Check if the application is still running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Start the navigation system
     */
    public void start(Command initialCommand) {
        navigateTo(initialCommand);
        
        while (isRunning && !navigationStack.isEmpty()) {
            try {
                Command currentCommand = getCurrentCommand();
                if (currentCommand != null) {
                    currentCommand.execute();
                    // After execution, if it's not a menu, we need to go back
                    // Menus handle their own navigation, actions need to go back
                    if (!(currentCommand instanceof command.MenuViewCommand)) {
                        goBack();
                    }
                } else {
                    break;
                }
            } catch (Exception e) {
                System.err.println("❌ An error occurred: " + e.getMessage());
                // On error, try to go back or exit if at root
                if (isAtRoot()) {
                    exit();
                } else {
                    goBack();
                }
            }
        }
    }

    /**
     * Clear the navigation stack
     */
    public void reset() {
        navigationStack.clear();
        isRunning = true;
    }
}

