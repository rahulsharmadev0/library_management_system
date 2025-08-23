package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.AppRoute;
import com.library_management_system.app.ui.utils.MenuViewCommand;

public class MemberManagementMenu extends MenuViewCommand {
    private static final Option[] MEMBER_OPTIONS = {
            new Option("Register New Member", "", AppRoute.RegisterNewMemberCommand),
            new Option("Update Member Info", "", AppRoute.UpdateMemberCommand),
            new Option("Delete Member", "", AppRoute.DeleteMemberCommand),
            new Option("Search Member", "", AppRoute.SearchMemberMenu),
            new Option("List All Members", "", AppRoute.ListAllMembersCommand),
    };

    public MemberManagementMenu() {
        super("MEMBER MANAGEMENT", MEMBER_OPTIONS);
    }

}
