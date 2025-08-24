package com.library_management_system.app.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.library_management_system.app.ui.adapters.TableFormatter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private Integer pages;

    public static Book of(String title, String author, String isbn, int pages) {
        return new Book(null, title, author, isbn, pages);
    }

    @Getter
    private static final TableFormatter<Book> tableFormat;

    static {
        tableFormat = TableFormatter.<Book>create()
                 .withIndex(false)
                .addColumn("Book ID", 10, Book::getId)
                .addColumn("Title", 20, Book::getTitle)
                .addColumn("Author", 20, Book::getAuthor)
                .addColumn("ISBN", 14, Book::getIsbn)
                .addColumn("Pages", 6, Book::getPages);
    }
}
