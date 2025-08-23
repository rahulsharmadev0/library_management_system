package ui.action.book;

import ui.command.ActionCommand;
import domain.entities.Book;
import domain.repositories.BookRepository;

import java.util.List;

public class ListAllBooksCommand extends ActionCommand {

    @Override
    protected void performAction() throws Exception {
        List<Book> books = BookRepository.getInstance().findAll();
        Book.getTableFormat().setTitle("📚 All Books in Library").display(books);

    }
}
