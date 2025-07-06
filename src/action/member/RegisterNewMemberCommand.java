package action.member;

import model.Member;
import service.MemberService;
import command.ActionCommand;

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
        if (MemberService.instance.findBy(s->s.id().equals(id)).isPresent()) {
            showMessage("Member with ID '" + id + "' already exists!", true);
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

        Member newMember = new Member(id, name, email, phone);
        MemberService.instance.add(newMember);
        showMessage("Member '" + name + "' has been successfully registered!", false);
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
