package action.member;

import model.Member;
import service.MemberService;
import command.ActionCommand;

import java.util.List;

// Done By AI (TODO: Self)
// TODO: Refactor, -> need to segregate table creation
public class ListAllMembersCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("ALL MEMBERS IN LIBRARY");
        
        List<Member> members = MemberService.instance.getMembers();
        
        if (members.isEmpty()) {
            System.out.println("👥 No members found in the library.");
            System.out.println("💡 Use 'Register New Member' option to add members to the library.");
            return;
        }
        
        System.out.printf("👤 Total Members: %d%n%n", members.size());
        
        // Table header
        System.out.println("┌─────┬──────────────┬──────────────────────┬──────────────────────┬─────────────────┐");
        System.out.printf("│ %-3s │ %-12s │ %-20s │ %-20s │ %-15s │%n", "No.", "Member ID", "Name", "Email", "Phone");
        System.out.println("├─────┼──────────────┼──────────────────────┼──────────────────────┼─────────────────┤");
        
        // Table rows
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            System.out.printf("│ %-3d │ %-12s │ %-20s │ %-20s │ %-15s │%n",
                i + 1,
                member.id(),
                truncateString(member.name(), 20),
                truncateString(member.email(), 20),
                truncateString(member.phone(), 15)
            );
        }
        
        System.out.println("└─────┴──────────────┴──────────────────────┴──────────────────────┴─────────────────┘");
    }
    
    private String truncateString(String str, int length) {
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 3) + "...";
    }
}
