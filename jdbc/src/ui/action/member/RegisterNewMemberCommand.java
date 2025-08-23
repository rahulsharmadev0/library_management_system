package ui.action.member;

import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;

public class RegisterNewMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("REGISTER NEW MEMBER");

        String name = getInput("Enter member name", true);
        String email = getInput("Enter email address", true);
        String phone = getInput("Enter phone number", true);

        Member newMember = new Member(0, name, email, phone);
        MemberRepository.getInstance().insert(newMember);
        showMessage("Member '" + name + "' has been successfully registered!", false);
    }
}
