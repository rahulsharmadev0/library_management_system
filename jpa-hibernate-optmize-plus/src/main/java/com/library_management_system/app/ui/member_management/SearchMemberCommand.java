package com.library_management_system.app.ui.member_management;

import java.util.List;

import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Member;
import com.library_management_system.app.domain.repositories.MemberRepository;
import com.library_management_system.app.ui.Route;

public class SearchMemberCommand {
    protected static void displaySearchResults(List<Member> results, String criteria) {
        System.out.println();
        System.out.println("🔍 Search Results for " + criteria + ":");
        System.out.println();

        Member.getTableFormat()
                .setTitle("👤 Found " + results.size() + " member(s)")
                .display(results, () -> {
                    System.out.println("👥 No members found matching your search criteria.");
                    System.out.println("💡 Try searching with different keywords or check the spelling.");
                });
    }

    @Route(title = "Search Member by ID", description = "Find a member using their unique ID number")
    public static class ById extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY ID");

            Input.get()
                    .takeString("Enter member ID to search")
                    .execute(inputs -> {
                        String searchTerm = inputs[0].toString();
                        if (searchTerm == null || searchTerm.isEmpty() || !searchTerm.matches("\\d+")) {
                            showMessage("Search term cannot be empty and must be a number", true);
                            return;
                        }
                        MemberRepository.getInstance()
                                .findById(Integer.parseInt(searchTerm))
                                .ifPresent(value -> SearchMemberCommand.displaySearchResults(
                                        List.of(value),
                                        "ID '" + searchTerm + "'"));
                    });
        }
    }

    @Route(title = "Search Member by Name", description = "Find members by searching their names")
    public static class ByName extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY NAME");

            Input.get()
                    .takeString("Enter name to search")
                    .execute(inputs -> {
                        String searchTerm = inputs[0].toString();
                        if (searchTerm == null || searchTerm.isEmpty()) {
                            showMessage("Search term cannot be empty", true);
                            return;
                        }
                        SearchMemberCommand.displaySearchResults(
                                MemberRepository.getInstance().findByName(searchTerm),
                                "name containing '" + searchTerm + "'");
                    });
        }
    }

    @Route(title = "Search Member by Email", description = "Find a member using their email address")
    public static class ByEmail extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY EMAIL");

            Input.get()
                    .takeString("Enter email to search")
                    .execute(inputs -> {
                        String searchTerm = inputs[0].toString();
                        if (searchTerm == null || searchTerm.isEmpty()) {
                            showMessage("Search term cannot be empty", true);
                            return;
                        }
                        SearchMemberCommand.displaySearchResults(
                                MemberRepository.getInstance().findByEmail(searchTerm),
                                "email containing '" + searchTerm + "'");
                    });
        }
    }

    @Route(title = "Search Member by Phone", description = "Find a member using their phone number")
    public static class ByPhone extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH MEMBER BY PHONE");

            Input.get()
                    .takeString("Enter phone number to search")
                    .execute(inputs -> {
                        String searchTerm = inputs[0].toString();
                        if (searchTerm == null || searchTerm.isEmpty()) {
                            showMessage("Search term cannot be empty", true);
                            return;
                        }
                        SearchMemberCommand.displaySearchResults(
                                MemberRepository.getInstance().findByPhone(searchTerm),
                                "phone containing '" + searchTerm + "'");
                    });
        }
    }
}