package domain.entities;

import java.time.LocalDate;
import utils.TableFormatter;

public record BookIssue(
    int id,
    int bookId,
    int memberId,
    LocalDate issueDate,
    LocalDate dueDate,
    LocalDate returnDate,
    IssueStatus status,
    String notes
) {
    
    public enum IssueStatus {
        ISSUED("📖 Issued"),
        RETURNED("✅ Returned"), 
        OVERDUE("⚠️ Overdue"),
        LOST("❌ Lost");
        
        private final String displayName;
        
        IssueStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    private static final TableFormatter<BookIssue> tableFormat = TableFormatter.<BookIssue>create()
            .addColumn("Issue ID", 10, BookIssue::id)
            .addColumn("Book ID", 10, BookIssue::bookId)
            .addColumn("Member ID", 12, BookIssue::memberId)
            .addColumn("Issue Date", 12, issue -> issue.issueDate().toString())
            .addColumn("Due Date", 12, issue -> issue.dueDate().toString())
            .addColumn("Status", 15, issue -> issue.status().getDisplayName())
            .addColumn("Notes", 20, issue -> issue.notes() != null ? issue.notes() : "");

    public static TableFormatter<BookIssue> getTableFormat() {
        return tableFormat;
    }
    
    public boolean isOverdue() {
        return status == IssueStatus.ISSUED && LocalDate.now().isAfter(dueDate);
    }
    
    public long getDaysOverdue() {
        if (!isOverdue()) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }
}
