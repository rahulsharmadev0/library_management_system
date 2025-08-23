package com.library_management_system.app.ui.adapters;

import java.util.Arrays;
import java.util.List;

import com.library_management_system.app.domain.entities.Book;
import com.library_management_system.app.domain.entities.Member;

/**
 * Demonstration of the generic TableFormatter showing code reduction benefits
 */
public class TableFormatterDemo {
    
    public static void main(String[] args) {
        demonstrateBookTable();
        System.out.println("\n" + "=".repeat(60) + "\n");
        demonstrateMemberTable();
        System.out.println("\n" + "=".repeat(60) + "\n");
        demonstrateStyleVariations();
    }
    
    private static void demonstrateBookTable() {
        System.out.println("📚 BOOK TABLE DEMONSTRATION");
        
        List<Book> books = Arrays.asList(
            new Book(0, "Java Programming", "John Doe", "978-1234567890", 350),
            new Book(1, "Database Design", "Jane Smith", "978-0987654321", 280),
            new Book(2,"Web Development", "Bob Johnson", "978-1111111111", 420)
        );
        
        // Create table with fluent API - one liner configuration!
        TableFormatter.<Book>create("LIBRARY BOOKS")
            .withStyle(TableFormatter.Style.LIGHT)
            .addColumn("Title", 18, Book::getTitle)
            .addColumn("Author", 15, Book::getAuthor)
            .addColumn("ISBN", 17, Book::getIsbn)
            .addColumn("Pages", 6, Book::getPages, TableFormatter.Alignment.RIGHT)
            .display(books);
    }
    
    private static void demonstrateMemberTable() {
        System.out.println("👥 MEMBER TABLE DEMONSTRATION");
        
        List<Member> members = Arrays.asList(
            new Member(0, "Alice Wilson", "alice@email.com", "555-0101"),
            new Member(1, "Charlie Brown", "charlie@email.com", "555-0202"),
            new Member(2, "Diana Prince", "diana@email.com", "555-0303")
        );
        
        // Same clean configuration for different data type
        TableFormatter.<Member>create("REGISTERED MEMBERS")
            .withStyle(TableFormatter.Style.LIGHT)
            .addColumn("ID", 8, Member::getId, TableFormatter.Alignment.CENTER)
            .addColumn("Name", 15, Member::getName)
            .addColumn("Email", 18, Member::getEmail)
            .addColumn("Phone", 12, Member::getPhone)
            .display(members);
    }
    
    private static void demonstrateStyleVariations() {
        System.out.println("🎨 STYLE VARIATIONS DEMONSTRATION");
        
        List<Book> books = Arrays.asList(
            new Book(0, "Sample Book", "Author Name", "123-456-789", 200)
        );
        
        // Basic style
        System.out.println("\n1. BASIC Style:");
        TableFormatter.<Book>create("Books - Basic Style")
            .withStyle(TableFormatter.Style.BASIC)
            .addColumn("Title", 15, Book::getTitle)
            .addColumn("Author", 12, Book::getAuthor)
            .addColumn("Pages", 6, Book::getPages, TableFormatter.Alignment.RIGHT)
            .display(books);
        
        // Minimal style
        System.out.println("\n2. MINIMAL Style:");
        TableFormatter.<Book>create("Books - Minimal Style")
            .withStyle(TableFormatter.Style.MINIMAL)
            .addColumn("Title", 15, Book::getTitle)
            .addColumn("Author", 12, Book::getAuthor)
            .addColumn("Pages", 6, Book::getPages, TableFormatter.Alignment.RIGHT)
            .display(books);
        
        // No index column
        System.out.println("\n3. Without Index Numbers:");
        TableFormatter.<Book>create("Books - No Index")
            .withStyle(TableFormatter.Style.LIGHT)
            .withIndex(false)
            .addColumn("Title", 15, Book::getTitle)
            .addColumn("Author", 12, Book::getAuthor)
            .addColumn("Pages", 6, Book::getPages, TableFormatter.Alignment.RIGHT)
            .display(books);
    }
}
