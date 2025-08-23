package com.library_management_system.app.ui;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.utils.MenuViewCommand;

public class MainMenu extends MenuViewCommand {
    private static final Option[] MAIN_OPTIONS = {
            new Option("Book Inventory Management", "", AppRoute.BookInventoryMenu),
            new Option("Member Management", "", AppRoute.MemberManagementMenu),
            new Option("Book Issuing & Returning", "", AppRoute.BookIssuingMenu),
    };

    public MainMenu() {
        super("LIBRARY MANAGEMENT SYSTEM", MAIN_OPTIONS);
    }
}
