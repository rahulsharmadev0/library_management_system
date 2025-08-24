package com.library_management_system.app.ui.book_issuing_returning;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.utils.MenuViewCommand;
import com.library_management_system.app.ui.utils.FeatureUnavailableCommand;

@Route(title = "Book Issuing & Returning", 
       description = "Issue and return books",
       children = {
           FeatureUnavailableCommand.class
       })
public class BookIssuingMenuCommand extends MenuViewCommand {

    public BookIssuingMenuCommand(String[] options) {
        super("📋 BOOK ISSUING & RETURNING", options);
    }
}
