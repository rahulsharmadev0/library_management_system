package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.ui.utils.MenuViewCommand;
import com.library_management_system.app.ui.Route;

@Route(title = "Member Management", 
       description = "Manage library members - register, update, delete, and search",
       children = {
           RegisterNewMemberCommand.class,
           ListAllMembersCommand.class,
           SearchMemberMenu.class,
           UpdateMemberCommand.class,
           DeleteMemberCommand.class
       })
public class MemberManagementMenu extends MenuViewCommand {

    public MemberManagementMenu(String[] options) {
        super("MEMBER MANAGEMENT", options);
    }

}
