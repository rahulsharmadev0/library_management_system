package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;

@Route(title = "List All Book Issues", description = "Display all book issues in the library")
public class ListAllBookIssuesCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("This feature is not implemented yet.");
    }
}
