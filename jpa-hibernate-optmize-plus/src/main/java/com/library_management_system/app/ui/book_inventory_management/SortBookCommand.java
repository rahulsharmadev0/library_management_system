package com.library_management_system.app.ui.book_inventory_management;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;
import com.library_management_system.app.domain.entities.Book;
import com.library_management_system.app.domain.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

// showMessage("📚 No books found in the library.");
// showMessage("💡 Use 'Add New Book' option to add books to the library.");

public class SortBookCommand {

    @Route(title = "Sort Book by Title", description = "Sort books in the library inventory by title")
    public static class ByTitle extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SORT BOOKS BY TITLE");

            List<Book> books = BookRepository.getInstance().findAll();


            List<Book> sortedBooks = books.stream()
                    .sorted((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()))
                    .collect(Collectors.toList());

            Book.getTableFormat()
                    .setTitle("📚 Books Sorted by Title (A-Z)")
                    .display(sortedBooks);
        }
    }

    @Route(title = "Sort Book by Author", description = "Sort books in the library inventory by author")
    public static class ByAuthor extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SORT BOOKS BY AUTHOR");

            List<Book> books = BookRepository.getInstance().findAll();

            List<Book> sortedBooks = books.stream()
                    .sorted((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()))
                    .collect(Collectors.toList());

            Book.getTableFormat()
                    .setTitle("📚 Books Sorted by Author (A-Z)")
                    .display(sortedBooks);
        }
    }

    @Route(title = "Sort Book by Pages", description = "Sort books in the library inventory by number of pages")
    public static class ByPages extends ActionCommand {
        @Override
        protected void performAction() throws Exception {
            displayHeader("SORT BOOKS BY PAGES");

            List<Book> books = BookRepository.getInstance().findAll();

            List<Book> sortedBooks = books.stream()
                    .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
                    .collect(Collectors.toList());

            Book.getTableFormat()
                    .setTitle("📚 Books Sorted by Pages (Low to High)")
                    .display(sortedBooks);
        }
    }
}
