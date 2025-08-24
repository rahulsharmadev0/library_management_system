package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Member;
import com.library_management_system.app.domain.repositories.MemberRepository;
import com.library_management_system.app.ui.Route;

@Route(title = "Delete Member", description = "Remove a member from the library system")
public class DeleteMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("DELETE MEMBER");
        Input.get()
                .takeInt("Enter member ID to delete")
                .execute((inputs) -> {
                    int memberId = (Integer) inputs[0];

                    MemberRepository repository = MemberRepository.getInstance();

                    Member memberToDelete = repository.findById(memberId).orElseThrow();

                    showMessage("Member ID: " + memberId);
                    showMessage("Member name: " + memberToDelete.getName());
                    showMessage("Are you sure you want to delete this member?");
                    showMessage("Enter 'y' to confirm or 'n' to cancel");
                    Input.get().takeString("Enter confirmation").execute((confirmationInputs) -> {
                        if (((String) confirmationInputs[0]).equalsIgnoreCase("y")) {
                            repository.delete(memberToDelete.getId());
                            showMessage("Member deleted successfully!", false);
                        } else {
                            showMessage("Member deletion cancelled.", true);
                        }
                    });
                });
    }
}
