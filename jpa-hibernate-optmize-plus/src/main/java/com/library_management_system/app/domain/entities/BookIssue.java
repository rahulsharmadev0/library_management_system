package com.library_management_system.app.domain.entities;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.*;
import com.library_management_system.app.ui.adapters.TableFormatter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_issues")
public class BookIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "member_id")
    private int memberId;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;
    private String notes;

    static BookIssue of(int bookId, int memberId, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate, IssueStatus status, String notes) {
        return new BookIssue(null, bookId, memberId, issueDate, dueDate, returnDate, status, notes);
    }

    @Getter
    enum IssueStatus {
        ISSUED("📖 Issued"),
        RETURNED("✅ Returned"),
        OVERDUE("⚠️ Overdue"),
        LOST("❌ Lost");

        private final String displayName;

        IssueStatus(String displayName) {
            this.displayName = displayName;
        }

    }

    @Getter
    private static final TableFormatter<BookIssue> tableFormat;

    static {
        tableFormat = TableFormatter.<BookIssue>create()
            .addColumn("Issue ID", 10, BookIssue::getId)
            .addColumn("Book ID", 10, BookIssue::getBookId)
            .addColumn("Member ID", 12, BookIssue::getMemberId)
            .addColumn("Issue Date", 12, issue -> issue.getIssueDate() != null ? issue.getIssueDate().toString() : "")
            .addColumn("Due Date", 12, issue -> issue.getDueDate() != null ? issue.getDueDate().toString() : "")
            .addColumn("Status", 15, issue -> issue.getStatus() != null ? issue.getStatus().getDisplayName() : "")
            .addColumn("Notes", 20, issue -> issue.getNotes() != null ? issue.getNotes() : "");
    }

    public boolean isOverdue() {
        return status == IssueStatus.ISSUED && LocalDate.now().isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (!isOverdue())
            return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }
}
