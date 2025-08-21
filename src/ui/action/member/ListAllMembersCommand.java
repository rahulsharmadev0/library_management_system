package ui.action.member;

import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;

import java.util.List;

public class ListAllMembersCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        displayHeader("ALL MEMBERS IN LIBRARY");
        
        List<Member> members = MemberRepository.getInstance().findAll();
        
        if (members.isEmpty()) {
            System.out.println("👥 No members found in the library.");
            System.out.println("💡 Use 'Register New Member' option to add members to the library.");
            return;
        }
        
        Member.getTableFormat().setTitle("👥 All Members in Library").display(members);
    }
}
