package ui.action.member;

import utils.TableFormatter;
import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;

import java.util.List;

public class ListAllMembersCommand extends ActionCommand {

    private static final TableFormatter<Member> MEMBER_TABLE = 
        TableFormatter.<Member>builder("ALL MEMBERS IN LIBRARY")
            .withStyle(TableFormatter.Style.LIGHT)
            .addColumn("Member ID", 12, Member::id)
            .addColumn("Name", 20, Member::name)
            .addColumn("Email", 20, Member::email)
            .addColumn("Phone", 15, Member::phone);

    @Override
    protected void performAction() throws Exception {
        displayHeader("ALL MEMBERS IN LIBRARY");
        
        List<Member> members = MemberRepository.instance.getAll();
        
        if (members.isEmpty()) {
            System.out.println("👥 No members found in the library.");
            System.out.println("💡 Use 'Register New Member' option to add members to the library.");
            return;
        }
        
        MEMBER_TABLE.display(members);
    }
}
