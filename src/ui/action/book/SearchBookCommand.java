package ui.action.book;

import java.util.*;
import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

public class SearchBookCommand {
    protected static void displaySearchResults(List<Book> results, String criteria) {
        System.out.println();
        System.out.println("🔍 Search Results for " + criteria + ":");
        System.out.println();

        if (results.isEmpty()) {
            System.out.println("📚 No Book found matching your search criteria.");
            System.out.println("💡 Try searching with different keywords or check the spelling.");
            return;
        }

        Book.getTableFormat()
                .setTitle("📚 Found " + results.size() + " book(s)")
                .display(results);
    }

    public static class ByTitle extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH BOOK BY TITLE");

            String searchTerm = getInput("Enter book title to search");
            if (searchTerm != null) {
                SearchBookCommand.displaySearchResults(
                        BookRepository.getInstance().findByTitle(searchTerm),
                        "title containing '" + searchTerm + "'");
            }
        }
    }

    public static class ByAuthor extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH BOOK BY AUTHOR");

            String searchTerm = getInput("Enter author name to search");
            if (searchTerm != null) {
                SearchBookCommand.displaySearchResults(
                        BookRepository.getInstance().findByAuthor(searchTerm),
                        "author containing '" + searchTerm + "'");
            }
        }
    }

    public static class ByISBN extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH BOOK BY ISBN");
            String searchTerm = getInput("Enter ISBN to search");
            if (searchTerm != null) {
                BookRepository.getInstance().findByIsbn(searchTerm).ifPresent(book -> {
                    SearchBookCommand.displaySearchResults(List.of(book), "ISBN '" + searchTerm + "'");
                });
            }
        }
    }
}