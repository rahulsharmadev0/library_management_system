package utils;

import java.util.Arrays;
import java.util.List;

import domain.entities.Book;
import domain.entities.Member;

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
            new Book("Java Programming", "John Doe", "978-1234567890", "350"),
            new Book("Database Design", "Jane Smith", "978-0987654321", "280"),
            new Book("Web Development", "Bob Johnson", "978-1111111111", "420")
        );
        
        // Create table with fluent API - one liner configuration!
        TableFormatter.<Book>builder("LIBRARY BOOKS")
            .withStyle(TableFormatter.Style.LIGHT)
            .addColumn("Title", 18, Book::title)
            .addColumn("Author", 15, Book::author)
            .addColumn("ISBN", 17, Book::isbn)
            .addColumn("Pages", 6, Book::pages, TableFormatter.Alignment.RIGHT)
            .display(books);
    }
    
    private static void demonstrateMemberTable() {
        System.out.println("👥 MEMBER TABLE DEMONSTRATION");
        
        List<Member> members = Arrays.asList(
            new Member("M001", "Alice Wilson", "alice@email.com", "555-0101"),
            new Member("M002", "Charlie Brown", "charlie@email.com", "555-0202"),
            new Member("M003", "Diana Prince", "diana@email.com", "555-0303")
        );
        
        // Same clean configuration for different data type
        TableFormatter.<Member>builder("REGISTERED MEMBERS")
            .withStyle(TableFormatter.Style.LIGHT)
            .addColumn("ID", 8, Member::id, TableFormatter.Alignment.CENTER)
            .addColumn("Name", 15, Member::name)
            .addColumn("Email", 18, Member::email)
            .addColumn("Phone", 12, Member::phone)
            .display(members);
    }
    
    private static void demonstrateStyleVariations() {
        System.out.println("🎨 STYLE VARIATIONS DEMONSTRATION");
        
        List<Book> books = Arrays.asList(
            new Book("Sample Book", "Author Name", "123-456-789", "200")
        );
        
        // Basic style
        System.out.println("\n1. BASIC Style:");
        TableFormatter.<Book>create("Books - Basic Style")
            .withStyle(TableFormatter.Style.BASIC)
            .addColumn("Title", 15, Book::title)
            .addColumn("Author", 12, Book::author)
            .addColumn("Pages", 6, Book::pages, TableFormatter.Alignment.RIGHT)
            .display(books);
        
        // Minimal style
        System.out.println("\n2. MINIMAL Style:");
        TableFormatter.<Book>create("Books - Minimal Style")
            .withStyle(TableFormatter.Style.MINIMAL)
            .addColumn("Title", 15, Book::title)
            .addColumn("Author", 12, Book::author)
            .addColumn("Pages", 6, Book::pages, TableFormatter.Alignment.RIGHT)
            .display(books);
        
        // No index column
        System.out.println("\n3. Without Index Numbers:");
        TableFormatter.<Book>create("Books - No Index")
            .withStyle(TableFormatter.Style.LIGHT)
            .withIndex(false)
            .addColumn("Title", 15, Book::title)
            .addColumn("Author", 12, Book::author)
            .addColumn("Pages", 6, Book::pages, TableFormatter.Alignment.RIGHT)
            .display(books);
    }
}
