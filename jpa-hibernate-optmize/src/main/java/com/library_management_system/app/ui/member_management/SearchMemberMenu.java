package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.domain.entities.Option;
import com.library_management_system.app.ui.AppRoute;
import com.library_management_system.app.ui.utils.MenuViewCommand;

public class SearchMemberMenu extends MenuViewCommand {
    private static final Option[] SEARCH_OPTIONS = {
            new Option("Search by Member ID", "Find member by ID number", AppRoute.SearchMemberCommandById),
            new Option("Search by Name", "Find members by name", AppRoute.SearchMemberCommandByName),
            new Option("Search by Email", "Find member by email address", AppRoute.SearchMemberCommandByEmail),
            new Option("Search by Phone", "Find member by phone number", AppRoute.SearchMemberCommandByPhone),
    };

    public SearchMemberMenu() {
        super("🔍 SEARCH MEMBERS", SEARCH_OPTIONS);
    }
}
