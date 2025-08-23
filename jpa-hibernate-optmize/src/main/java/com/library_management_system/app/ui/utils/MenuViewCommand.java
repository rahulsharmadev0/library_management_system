package com.library_management_system.app.ui.utils;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.AppRoute;
import com.library_management_system.app.ui.Navigator;
import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;

public abstract class MenuViewCommand extends ActionCommand {
    protected final String title;
    protected final Option[] options;

    public MenuViewCommand(String title, Option[] options) {
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
                            // the current menu
                            if (selectedCommand.get() instanceof MenuViewCommand) {
                                navigator.navigateTo(selectedCommand);
                            } else {
                                // TODO: Here it call Command
                                selectedCommand.get().run();
                                // After action execution, re-execute current menu
                                run();
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
                        run(); // Re-show menu
                    } catch (Exception e) {
                        showMessage("An error occurred: " + e.getMessage(), true);
                        run(); // Re-show menu
                    }
                });

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
