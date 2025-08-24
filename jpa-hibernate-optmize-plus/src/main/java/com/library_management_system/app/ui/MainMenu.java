package com.library_management_system.app.ui;

import com.library_management_system.app.ui.utils.MenuViewCommand;
import com.library_management_system.app.ui.book_inventory_management.BookInventoryMenuCommand;
import com.library_management_system.app.ui.book_issuing_returning.BookIssuingMenuCommand;
import com.library_management_system.app.ui.member_management.MemberManagementMenu;

@Route(title = "Main Menu", description = "Welcome to the Library Management System", children = {
        BookInventoryMenuCommand.class,
        MemberManagementMenu.class,
        BookIssuingMenuCommand.class})
public class MainMenu extends MenuViewCommand {
    public MainMenu(String[] options) {
        super("LIBRARY MANAGEMENT SYSTEM", options);
    }
}
