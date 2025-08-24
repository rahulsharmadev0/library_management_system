package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Member;
import com.library_management_system.app.domain.repositories.MemberRepository;
import com.library_management_system.app.ui.Route;

@Route(title = "Register New Member", description = "Add a new member to the library system")
public class RegisterNewMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("REGISTER NEW MEMBER");

        Input.get()
                .takeString("Enter member name")
                .takeString("Enter email address")
                .takeString("Enter phone number")
                .execute(inputs -> {
                    Member newMember = Member.of(
                            (String) inputs[0],
                            (String) inputs[0],
                            (String) inputs[0]);
                    MemberRepository.getInstance().insert(newMember);
                    showMessage("Member '" + newMember.getName() + "' has been successfully registered!", false);

                });


    }
}
