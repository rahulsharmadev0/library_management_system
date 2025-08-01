package ui.action.member;

import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchMemberCommand {

    // Common display method for all search types
    protected static void displaySearchResults(List<Member> results, String searchCriteria) {
        System.out.println();
        System.out.println("🔍 Search Results for " + searchCriteria + ":");
        System.out.println();

        if (results.isEmpty()) {
            System.out.println("👥 No members found matching your search criteria.");
            System.out.println("💡 Try searching with different keywords or check the spelling.");
            return;
        }

        System.out.printf("👤 Found %d member(s):%n%n", results.size());

        // Table header
        System.out.println("┌─────┬──────────────┬──────────────────────┬──────────────────────┬─────────────────┐");
        System.out.printf("│ %-3s │ %-12s │ %-20s │ %-20s │ %-15s │%n", "No.", "Member ID", "Name", "Email", "Phone");
        System.out.println("├─────┼──────────────┼──────────────────────┼──────────────────────┼─────────────────┤");

        // Table rows
        for (int i = 0; i < results.size(); i++) {
            Member member = results.get(i);
            System.out.printf("│ %-3d │ %-12s │ %-20s │ %-20s │ %-15s │%n",
                    i + 1,
                    member.id(),
                    truncateString(member.name(), 20),
                    truncateString(member.email(), 20),
                    truncateString(member.phone(), 15));
        }

        System.out.println("└─────┴──────────────┴──────────────────────┴──────────────────────┴─────────────────┘");
    }

    protected static String truncateString(String str, int length) {
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 3) + "...";
    }

    public static class ById extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY ID");

            String searchTerm = getInput("Enter member ID to search");
            if (searchTerm.isEmpty()) {
                showMessage("Search term cannot be empty", true);
                return;
            }

            List<Member> members = MemberRepository.getInstance().getAll();
            List<Member> results = members.stream()
                    .filter(member -> String.valueOf(member.id()).equalsIgnoreCase(searchTerm))
                    .collect(Collectors.toList());

            SearchMemberCommand.displaySearchResults(results, "ID '" + searchTerm + "'");
        }
    }

    public static class ByName extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY NAME");

            String searchTerm = getInput("Enter name to search");
            if (searchTerm.isEmpty()) {
                showMessage("Search term cannot be empty", true);
                return;
            }

            List<Member> members = MemberRepository.getInstance().getAll();
            List<Member> results = members.stream()
                    .filter(member -> member.name().toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());

            SearchMemberCommand.displaySearchResults(results, "name containing '" + searchTerm + "'");
        }
    }

    public static class ByEmail extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY EMAIL");

            String searchTerm = getInput("Enter email to search");
            if (searchTerm.isEmpty()) {
                showMessage("Search term cannot be empty", true);
                return;
            }

            List<Member> members = MemberRepository.getInstance().getAll();
            List<Member> results = members.stream()
                    .filter(member -> member.email().toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());

            SearchMemberCommand.displaySearchResults(results, "email containing '" + searchTerm + "'");
        }
    }

    public static class ByPhone extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY PHONE");

            String searchTerm = getInput("Enter phone number to search");
            if (searchTerm.isEmpty()) {
                showMessage("Search term cannot be empty", true);
                return;
            }

            List<Member> members = MemberRepository.getInstance().getAll();
            List<Member> results = members.stream()
                    .filter(member -> member.phone().contains(searchTerm))
                    .collect(Collectors.toList());

            SearchMemberCommand.displaySearchResults(results, "phone containing '" + searchTerm + "'");
        }
    }
}
