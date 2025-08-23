package com.library_management_system.app.ui.adapters.commands;
import com.library_management_system.app.ui.adapters.Input;

import java.util.Scanner;


/**
 * Base class for action commands that perform specific operations
 * and return to the previous menu
 */
public abstract class ActionCommand implements Command {
    protected final Scanner scanner;
    

    public ActionCommand() {
        this.scanner = new Scanner(System.in);
   }

   protected void showMessage(String message) {
          System.out.println(message);
    }

   protected void showMessage(String message, boolean isError) {
        String icon = isError ? "❌" : "✅";
        System.out.println("\n" + icon + " " + message);
    }

    protected void displayHeader(String title) {
        String border = "═".repeat(Math.max(title.length() + 4, 50));
        System.out.println("\n" + border);
        System.out.println("║ " + centerText(title, border.length() - 4) + " ║");
        System.out.println(border);
    }
    
    private String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }

    @Override
    public final void run()  {
        try {
            performAction();
        } catch (Exception e) {
            showMessage("Action failed: " + e.getMessage(), true);
            Input.get().waitForEnter();
        }
    }

    /**
     * Implement this method to define the specific action
     */
    protected abstract void performAction() throws Exception;
}
