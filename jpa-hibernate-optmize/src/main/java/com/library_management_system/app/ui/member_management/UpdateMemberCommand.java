package com.library_management_system.app.ui.member_management;

import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Member;
import com.library_management_system.app.domain.repositories.MemberRepository;
import com.library_management_system.app.ui.adapters.Input;

import java.util.List;

public class UpdateMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("UPDATE MEMBER INFORMATION");

        Input.get()
                .takeInt("Enter Member ID to update: ")
                .execute((inputs) -> {
                    showMessage("Searching for member...");

                    MemberRepository.getInstance().findById((Integer) inputs[0]).ifPresent(
                            member -> {
                                Member.getTableFormat().setTitle("✅ Member Found").display(List.of(member));
                                updateMemberDetails(member);
                            }
                    );
                });
    }

    private void updateMemberDetails(Member member) {
        Input.get()
                .takeString("Enter new name (leave empty to keep current)")
                .takeString("Enter new email (leave empty to keep current)")
                .takeString("Enter new phone (leave empty to keep current)")
                .execute((inputs) -> {
                    Member updatedMember = new Member(
                            member.getId(),
                            inputs[0].toString().isBlank() ? member.getName() : inputs[0].toString(),
                            inputs[1].toString().isBlank() ? member.getEmail() : inputs[1].toString(),
                            inputs[2].toString().isBlank() ? member.getPhone() : inputs[2].toString()
                    );
                    MemberRepository.getInstance().update(updatedMember);
                    System.out.println("✅ Member updated successfully!");
                });
    }
}