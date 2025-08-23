package domain.entities;

import utils.TableFormatter;

public record Book(int id, String title, String author, String isbn, int pages) {

    private static TableFormatter<Book> tableFormat = TableFormatter.<Book>create()
            .addColumn("Title", 20, Book::title)
            .addColumn("Author", 20, Book::author)
            .addColumn("ISBN", 14, Book::isbn)
            .addColumn("Pages", 6, Book::pages);

    public static TableFormatter<Book> getTableFormat() {
        return tableFormat;     
    }
}
