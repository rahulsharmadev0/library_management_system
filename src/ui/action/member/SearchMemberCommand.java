package ui.action.member;

import ui.command.ActionCommand;
import domain.entities.Member;
import domain.repositories.MemberRepository;
import java.util.List;

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

        Member.getTableFormat()
                .setTitle("👤 Found " + results.size() + " member(s)")
                .display(results);
    }

    public static class ById extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY ID");

            String searchTerm = getInput("Enter member ID to search");
            if (searchTerm.isEmpty() || !searchTerm.matches("\\d+")) {
                showMessage("Search term cannot be empty", true);
                return;
            }

            MemberRepository.getInstance()
                    .findById(Integer.parseInt(searchTerm))
                    .ifPresent(value -> SearchMemberCommand.displaySearchResults(
                            List.of(value),
                            "ID '" + searchTerm + "'"));

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

            SearchMemberCommand.displaySearchResults(
                    MemberRepository.getInstance().findByName(searchTerm),
                    "name containing '" + searchTerm + "'");
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

            SearchMemberCommand.displaySearchResults(
                    MemberRepository.getInstance().findByEmail(searchTerm),
                    "email containing '" + searchTerm + "'");
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

            SearchMemberCommand.displaySearchResults(
                    MemberRepository.getInstance().findByPhone(searchTerm),
                    "phone containing '" + searchTerm + "'");
        }
    }
}
