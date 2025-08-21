package ui.action.member;

import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;

import java.util.List;

public class UpdateMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("UPDATE MEMBER INFORMATION");

        MemberRepository repository = MemberRepository.getInstance();
        List<Member> members = repository.findAll();

        if (members.isEmpty()) {
            System.out.println("👥 No members found in the library.");
            System.out.println("💡 Use 'Register New Member' option to add members first.");
            return;
        }

        // Display current members
        Member.getTableFormat().setTitle("👥 Current Members").display(members);

        String indexStr = getInput("Enter member number to update (1-" + members.size() + ")");

        try {
            int index = Integer.parseInt(indexStr) - 1;

            if (index < 0 || index >= members.size()) {
                showMessage("Invalid member number. Please enter a number between 1 and " + members.size(), true);
                return;
            }

            Member currentMember = members.get(index);

            System.out.println("Current information for: " + currentMember.name());
            System.out.println("Leave field empty to keep current value");
            System.out.println();

            String newName = getInput("Enter new name [" + currentMember.name() + "]");
            String newEmail = getInput("Enter new email [" + currentMember.email() + "]");
            String newPhone = getInput("Enter new phone [" + currentMember.phone() + "]");

            Member updatedMember = new Member(currentMember.id(),
                    newName == null ? currentMember.name() : newName,
                    newEmail == null ? currentMember.email() : newEmail,
                    newPhone == null ? currentMember.phone() : newPhone);

            repository.update(updatedMember);

            showMessage("Member information updated successfully!", false);

        } catch (NumberFormatException e) {
            showMessage("Invalid input. Please enter a valid number.", true);
        }
    }
}
