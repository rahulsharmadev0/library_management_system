package com.library_management_system.app.ui.utils;

import com.library_management_system.app.ui.Navigator;
import com.library_management_system.app.ui.Route.RouteData;
import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.ui.adapters.commands.Command;

public abstract class MenuViewCommand extends ActionCommand {
    private final String title;
    private final String[] options;

    public MenuViewCommand(String title, String[] options) {
        this.title = title;
        this.options = options;
    }

    @Override
    public void performAction() throws Exception {
        displayHeader(title);
        displayMenuOptions();

        Input.get().takeInt("")
                .execute(inputs -> {
                    try {
                        int choice = (Integer) inputs[0];
                        Navigator navigator = Navigator.get();

                        int totalOptions = options.length + (navigator.isAtRoot() ? 1 : 2);

                        if (choice < 1 || choice > totalOptions) {
                            showMessage("Invalid choice. Please select between 1 and " + totalOptions, true);
                            return;
                        }

                        if (choice <= options.length) {
                            String selectedClass = options[choice - 1];
                            RouteData selectedCommand = navigator.getRoute(selectedClass);
                            if (selectedCommand == null) {
                                showMessage("Route not found: " + selectedClass, true);
                                run();
                                return;
                            }
                            
                            // Check if it's a MenuViewCommand class
                            if (MenuViewCommand.class.isAssignableFrom(selectedCommand.type())) {
                                navigator.navigateTo(selectedCommand);
                            } else {
                                // Create and run the command instance
                                try {
                                    Command commandInstance = selectedCommand.type().getDeclaredConstructor().newInstance();
                                    commandInstance.run();
                                    run();
                                } catch (Exception e) {
                                    showMessage("Error running command: " + e.getMessage(), true);
                                    run();
                                }
                            }
                            return;
                        }

                        // Handle navigation options
                        choice -= options.length;

                        if (!navigator.isAtRoot() && choice == 1) {
                            navigator.goBack();
                        } else if ((navigator.isAtRoot() && choice == 1) || (!navigator.isAtRoot() && choice == 2)) {
                            navigator.exit();
                        }

                    } catch (NumberFormatException e) {
                        showMessage("Please enter a valid number", true);
                        run();
                    } catch (Exception e) {
                        showMessage("An error occurred: " + e.getMessage(), true);
                        run();
                    }
                });

    }

    private void displayMenuOptions() {
        int i = 0;
        while (i < options.length) {
            String className = options[i];
            String title = Navigator.get().getTitle(className);
            System.out.println((i + 1) + ". " + title);
            i++;
        }

        if (!Navigator.get().isAtRoot()) {
            System.out.println((i + 1) + ". 🔙 Go Back");
            System.out.println((i + 2) + ". 🚪 Exit");
        } else {
            System.out.println((i + 1) + ". 🚪 Exit");
        }
    }
}
