package action.member;

import model.Member;
import service.MemberService;
import command.ActionCommand;

import java.util.List;

// TODO: Refactor
public class DeleteMemberCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("DELETE MEMBER");
        
        List<Member> members = MemberService.instance.getMembers();
        
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
        
        String indexStr = getInput("Enter member number to delete (1-" + members.size() + ")");
        
        try {
            int index = Integer.parseInt(indexStr) - 1;
            
            if (index < 0 || index >= members.size()) {
                showMessage("Invalid member number. Please enter a number between 1 and " + members.size(), true);
                return;
            }
            
            Member memberToDelete = members.get(index);
            
            // Confirmation prompt
            boolean confirmation = confirmAction("Are you sure you want to delete member " + memberToDelete.name());
            
            if (confirmation) {
                Member deletedMember = MemberService.instance.removeMember(index);
                showMessage("Member '" + deletedMember.name() + "' has been successfully deleted from the library!", false);
            } else {
                showMessage("Delete operation cancelled.", false);
            }
            
        } catch (NumberFormatException e) {
            showMessage("Invalid input. Please enter a valid number.", true);
        }
    }
}
