package ui.action.member;

import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;

public class RegisterNewMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("REGISTER NEW MEMBER");

        String id = getInput("Enter member ID");
        if (id.isEmpty()) {
            showMessage("Member ID cannot be empty", true);
            return;
        }
        
        // Check if member ID already exists
        try {
            MemberRepository repository = MemberRepository.getInstance();
            if (repository.findById(Integer.parseInt(id)) != null) {
                showMessage("Member with ID '" + id + "' already exists!", true);
                return;
            }
        } catch (NumberFormatException e) {
            showMessage("Member ID must be a valid number", true);
            return;
        }
        
        String name = getInput("Enter member name");
        if (name.isEmpty()) {
            showMessage("Member name cannot be empty", true);
            return;
        }
        
        String email = getInput("Enter email address");
        if (email.isEmpty()) {
            showMessage("Email address cannot be empty", true);
            return;
        }
        
        // Basic email validation
        if (!isValidEmail(email)) {
            showMessage("Please enter a valid email address", true);
            return;
        }
        
        String phone = getInput("Enter phone number");
        if (phone.isEmpty()) {
            showMessage("Phone number cannot be empty", true);
            return;
        }

        Member newMember = new Member(0, name, email, phone);
        MemberRepository.getInstance().insert(newMember);
        showMessage("Member '" + name + "' has been successfully registered!", false);
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
