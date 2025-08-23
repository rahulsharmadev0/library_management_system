package ui.action.member;

import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;

import java.util.List;

public class UpdateMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("UPDATE MEMBER INFORMATION");
        
        List<Member> members = MemberRepository.instance.getAll();
        
        if (members.isEmpty()) {
            System.out.println("👥 No members found in the library.");
            System.out.println("💡 Use 'Register New Member' option to add members first.");
            return;
        }
        
        // Display current members
        System.out.println("📋 Current Members:");
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            System.out.printf("%d. %s (ID: %s, Email: %s, Phone: %s)%n", 
                i + 1, member.name(), member.id(), member.email(), member.phone());
        }
        System.out.println();
        
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
            
            // Get new values (allowing empty to keep current)
            String newName = getInput("Enter new name [" + currentMember.name() + "]");
            if (newName.isEmpty()) {
                newName = currentMember.name();
            }
            
            String newEmail = getInput("Enter new email [" + currentMember.email() + "]");
            if (newEmail.isEmpty()) {
                newEmail = currentMember.email();
            } else if (!isValidEmail(newEmail)) {
                showMessage("Please enter a valid email address", true);
                return;
            }
            
            String newPhone = getInput("Enter new phone [" + currentMember.phone() + "]");
            if (newPhone.isEmpty()) {
                newPhone = currentMember.phone();
            }
            
            Member updatedMember = new Member(currentMember.id(), newName, newEmail, newPhone);
            MemberRepository.instance.update(updatedMember, s-> s.id().equals(currentMember.id()));
            
            showMessage("Member information updated successfully!", false);
            
        } catch (NumberFormatException e) {
            showMessage("Invalid input. Please enter a valid number.", true);
        }
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
