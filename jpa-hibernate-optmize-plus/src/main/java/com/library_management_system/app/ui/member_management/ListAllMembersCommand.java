package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Member;
import com.library_management_system.app.domain.repositories.MemberRepository;
import com.library_management_system.app.ui.Route;

@Route(title = "List All Members", description = "Display all registered library members")
public class ListAllMembersCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("ALL MEMBERS IN LIBRARY");

        Member.getTableFormat()
                .setTitle("👥 All Members in Library")
                .display(MemberRepository.getInstance().findAll(),
                        () -> {
                            showMessage("👥No members found in the library.");
                            showMessage("Use 'Register New Member' option to add members first.");
                        });
    }
}
