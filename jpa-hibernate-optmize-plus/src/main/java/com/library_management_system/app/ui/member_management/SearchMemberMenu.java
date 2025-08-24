package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.ui.utils.MenuViewCommand;
import com.library_management_system.app.ui.Route;

@Route(title = "Search Members", 
       description = "Find members by different criteria like ID, name, email, or phone",
       children = {
           SearchMemberCommand.ById.class,
           SearchMemberCommand.ByName.class,
           SearchMemberCommand.ByEmail.class,
           SearchMemberCommand.ByPhone.class
       })
public class SearchMemberMenu extends MenuViewCommand {
    public SearchMemberMenu(String[] options) {
        super("🔍 SEARCH MEMBERS", options);
    }
}
