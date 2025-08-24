package com.library_management_system.app.ui.book_inventory_management;

import java.util.*;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.adapters.Input;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Book;
import com.library_management_system.app.domain.repositories.BookRepository;

public class SearchBookCommand {
    protected static void displaySearchResults(List<Book> results, String criteria) {
        System.out.println();
        System.out.println("🔍 Search Results for " + criteria + ":");
        System.out.println();

        Book.getTableFormat()
                .setTitle("📚 Found " + results.size() + " book(s)")
                .display(results, () -> {
                    System.out.println("📚 No Book found matching your search criteria.");
                    System.out.println("💡 Try searching with different keywords or check the spelling.");

                });
    }

    @Route(title = "Search Book by Title", description = "Search for books by title in the library inventory")
    public static class ByTitle extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH BOOK BY TITLE");

            Input.get()
                    .takeString("Enter book title to search")
                    .execute((inputs) -> {
                        String searchTerm = inputs[0].toString();
                        SearchBookCommand.displaySearchResults(
                                BookRepository.getInstance().findByTitle(searchTerm),
                                "title containing '" + searchTerm + "'");

                    });

        }
    }

    @Route(title = "Search Book By Author", description = "Search for books by author in the library inventory")
    public static class ByAuthor extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH BOOK BY AUTHOR");

            Input.get()
                    .takeString("Enter author name to search")
                    .execute((inputs) -> {
                        String searchTerm = inputs[0].toString();
                        SearchBookCommand.displaySearchResults(
                                BookRepository.getInstance().findByAuthor(searchTerm),
                                "author containing '" + searchTerm + "'");

                    });
        }
    }

    @Route(title = "Search Book By ISBN", description = "Search for books by ISBN in the library inventory")
    public static class ByISBN extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SEARCH BOOK BY ISBN");

            Input.get()
                    .takeString("Enter ISBN to search")
                    .execute((inputs) -> {
                        String searchTerm = inputs[0].toString();
                        BookRepository.getInstance().findByIsbn(searchTerm).ifPresent(book -> {
                            SearchBookCommand.displaySearchResults(
                                    List.of(book),
                                    "ISBN '" + searchTerm + "'");
                        });
                    });
        }
    }
}