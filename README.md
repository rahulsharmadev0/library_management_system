# Library Management System

A modern console-based Library Management System built with Java and PostgreSQL, demonstrating clean architecture principles, design patterns, and professional software development practices.

## 📑 Project Overview

This Library Management System is a learning-focused project that showcases the implementation of enterprise-level Java applications with database integration. The project demonstrates modern software engineering concepts including clean architecture, design patterns, JDBC database operations, and Docker containerization.

**🎯 Learning Objectives:**

- Master JDBC database operations and connection management
- Implement enterprise design patterns (Singleton, Repository, Command, Factory)
- Practice clean code architecture and separation of concerns
- Learn Docker containerization for database services
- Build reusable and maintainable console applications

## ✨ Currently Implemented Features

### 📚 Book Inventory Management

- ✅ **Add New Book**
- ✅ **List All Books**
- ✅ **Update Book Details**
- ✅ **Delete Book**
- ✅ **Search Functionality**
- ✅ **Sort Books**

### 👥 Member Management 

- ✅ **Register New Member**
- ✅ **List All Members**
- ✅ **Update Member Info**
- ✅ **Delete Member**
- ✅ **Search Functionality**

### 🏗️ System Infrastructure

- ✅ **Navigation System**: Complete menu-driven interface with back/exit functionality
- ✅ **Database Integration**: PostgreSQL with Docker containerization
- ✅ **Generic Repository Pattern**: Reusable JDBC operations
- ✅ **Table Formatter**: Generic utility for consistent data display
- ✅ **Command Pattern**: Extensible command system for UI actions

### � Not Yet Implemented

- ❌ **Book Issuing System**: Placeholder commands only
- ❌ **Book Search Functions**: Menu exists but no implementation
- ❌ **Book Sorting**: Menu exists but no implementation  
- ❌ **Advanced Reporting**: Not started
- ❌ **Data Import/Export**: Not implemented

## 🏗️ Architecture & Design Patterns

### Clean Architecture Implementation

```
┌─────────────────────────────────────────────────────────┐
│                    UI Layer                             │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────────┐│
│  │ Navigator   │ │ AppRoute    │ │ Command Pattern     ││
│  │             │ │             │ │ - MenuViewCommand   ││
│  │             │ │             │ │ - ActionCommand     ││
│  └─────────────┘ └─────────────┘ └─────────────────────┘│
└─────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────┐
│                 Domain Layer                            │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────────┐│
│  │ Entities    │ │ Repositories│ │ Services            ││
│  │ - Book      │ │ - BookRepo  │ │ - DatabaseManager   ││
│  │ - Member    │ │ - MemberRepo│ │ - (Singleton)       ││
│  │ - Option    │ │ - JdbcRepo  │ │                     ││
│  └─────────────┘ └─────────────┘ └─────────────────────┘│
└─────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────┐
│                Infrastructure                           │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────────┐│
│  │ PostgreSQL  │ │ Docker      │ │ TableFormatter      ││
│  │ Database    │ │ Container   │ │ (Generic Utility)   ││
│  └─────────────┘ └─────────────┘ └─────────────────────┘│
└─────────────────────────────────────────────────────────┘
```

### 🧩 Design Patterns Implemented

1. **Singleton Pattern**
   - `DataBaseManager`: Ensures single database connection instance
   - `BookRepository` & `MemberRepository`: Single repository instances

2. **Repository Pattern**  
   - `JdbcRepository<T>`: Generic base class for database operations
   - Abstracts database access from business logic
   - Consistent CRUD operations across entities

3. **Command Pattern**
   - `Command` interface with `execute()` method
   - `ActionCommand` & `MenuViewCommand` base implementations
   - Encapsulates user actions as objects

4. **Factory Pattern** (via Enums)
   - `AppRoute` enum creates command instances using Supplier pattern
   - Centralized command creation and routing

5. **Generic Programming**
   - `TableFormatter<T>`: Type-safe, reusable table display utility
   - `JdbcRepository<T>`: Generic database operations

## 🔧 Technology Stack & Learning Highlights

### Core Technologies

- **Java 21**: Modern Java features including Records for immutable data models
- **PostgreSQL**: Enterprise-grade database with proper schema design
- **JDBC**: Raw database connectivity (no ORM dependency)
- **Docker**: Containerized database for consistent development environment

### Key Learning Areas

- **Database Design**: Proper normalization, constraints, and indexing
- **Connection Management**: Singleton pattern with automatic retry logic
- **Error Handling**: Comprehensive exception management throughout the application
- **Code Organization**: Clean separation of concerns and package structure
- **User Experience**: Intuitive console interface with formatted output

### Development Tools

- **Docker Compose**: Infrastructure as code for database setup
- **Makefile**: Build automation and task management
- **VS Code Tasks**: IDE integration for seamless development

- **Language**: Java
- **Database**: PostgreSQL (Docker containerized)
- **UI**: Command-line interface with formatted text tables
- **Build Tool**: Manual compilation (VS Code tasks)
- **Containerization**: Docker Compose

## 📁 Project Structure Deep Dive

```
library_management_system/
├── 📄 README.md                 # Comprehensive project documentation
├── 📄 LICENSE                   # MIT License
├── 📄 Makefile                  # Enhanced build automation with database management
├── 📄 docker-compose.yml        # PostgreSQL containerization
├── 📄 init.sql                  # Database schema and sample data
├── 📄 TODO.md                   # Development roadmap and completed features
├── 
├── 📁 src/                      # Source code organization
│   ├── 📄 Main.java             # Application entry point
│   │
│   ├── 📁 domain/               # Business logic layer
│   │   ├── 📁 entities/         # Data models (Java Records)
│   │   │   ├── Book.java        # ✅ Immutable book entity
│   │   │   ├── Member.java      # ✅ Immutable member entity  
│   │   │   └── Option.java      # ✅ Menu option definition
│   │   │
│   │   ├── 📁 repositories/     # Data access layer
│   │   │   ├── JdbcRepository.java      # ✅ Generic JDBC base class
│   │   │   ├── BookRepository.java      # ✅ Book-specific operations
│   │   │   └── MemberRepository.java    # ✅ Member-specific operations
│   │   │
│   │   └── 📁 services/         # Business services
│   │       └── DataBaseManager.java     # ✅ Singleton connection manager
│   │
│   ├── 📁 ui/                   # User interface layer
│   │   ├── 📄 AppRoute.java     # ✅ Navigation routing & menu definitions
│   │   ├── 📄 Navigator.java    # ✅ Stack-based screen navigation
│   │   │
│   │   ├── 📁 command/          # Command pattern implementation
│   │   │   ├── Command.java             # ✅ Base command interface
│   │   │   ├── ActionCommand.java       # ✅ Action command base class
│   │   │   ├── MenuViewCommand.java     # ✅ Menu command base class
│   │   │   └── PlaceHolderCommand.java  # ✅ Placeholder for unimplemented features
│   │   │
│   │   └── 📁 action/           # Concrete command implementations
│   │       ├── 📁 book/         # Book-related commands
│   │       │   ├── AddBookCommand.java      # ✅ Fully implemented
│   │       │   ├── ListAllBooksCommand.java # ✅ With table formatting
│   │       │   ├── UpdateBookCommand.java   # ✅ Complete CRUD operation
│   │       │   ├── DeleteBookCommand.java   # ✅ Fully implemented
│   │       │   ├── SearchBookCommand.java   # ✅ Fully implemented
│   │       │   └── SortBookCommand.java     # ✅ Fully implemented
│   │       │
│   │       └── 📁 member/       # Member-related commands
│   │           ├── RegisterNewMemberCommand.java # ✅ With validation
│   │           ├── ListAllMembersCommand.java    # ✅ Professional display
│   │           ├── UpdateMemberCommand.java      # ✅ Complete functionality
│   │           ├── DeleteMemberCommand.java      # ✅ With confirmation
│   │           └── SearchMemberCommand.java      # ✅ Multiple search types
│   │
│   └── 📁 utils/                # Utility classes
│       ├── 📄 TableFormatter.java       # ✅ Generic table display utility
│       └── 📄 TableFormatterDemo.java   # ✅ Usage examples and demos
│
├── 📁 bin/                      # Compiled Java classes (generated)
├── 📁 lib/                      # External dependencies
│   └── postgresql-42.7.7.jar   # ✅ PostgreSQL JDBC driver
│
└── 📁 .idea/                    # IntelliJ IDEA project files
    ├── modules.xml
    ├── misc.xml
    └── vcs.xml
```


### Sample User Interaction

```
📚 Welcome to Library Management System
══════════════════════════════════════════════════════

╔═══════════════════════════════════════════════════╗
║              LIBRARY MANAGEMENT SYSTEM           ║
╚═══════════════════════════════════════════════════╝

1. Book Inventory Management
2. Member Management  
3. Book Issuing & Returning

👉 Enter your choice: 1

╔═══════════════════════════════════════════════════╗
║            BOOK INVENTORY MANAGEMENT             ║
╚═══════════════════════════════════════════════════╝

1. Add New Book
2. Delete Book
3. Update Book Details
4. Search Book
5. List All Books
6. Sort Books
7. 🔙 Go Back
8. 🚪 Exit

👉 Enter your choice: 5

ALL BOOKS IN LIBRARY
Total: 5

┌────┬────────────────────┬────────────────────┬──────────────┬──────┐
│No. │ Title              │ Author             │ ISBN         │Pages │
├────┼────────────────────┼────────────────────┼──────────────┼──────┤
│  1 │ The Great Gatsby   │ F. Scott Fitzge... │ 978-0-7432...│   218│
│  2 │ To Kill a Mocking  │ Harper Lee         │ 978-0-06-1...│   281│
│  3 │ 1984               │ George Orwell      │ 978-0-452-...│   328│
│  4 │ Pride and Prejud   │ Jane Austen        │ 978-0-14-1...│   279│
│  5 │ The Catcher in ... │ J.D. Salinger      │ 978-0-316-...│   277│
└────┴────────────────────┴────────────────────┴──────────────┴──────┘

📱 Press Enter to continue...
```

## 🚀 Getting Started

### Prerequisites

- **Java 21 or higher** (uses Records and modern language features)
- **Docker & Docker Compose** (for PostgreSQL database)
- **Terminal/Command Line** interface

### Quick Start

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd library_management_system
   ```

2. **Setup, Compile and Run:**

   ```bash
   make run # for more detitails, see Makefile
   ```

### Database Configuration

**Docker Setup (Recommended):**

- Database: `library_management_system`
- User: `lms_user`
- Password: `lms_password`
- Port: `5432`
- Container: `lms-postgres`

The database automatically initializes with:

- Required table schemas (`book`, `member`)
- Sample data for immediate testing
- Proper indexes and constraints

## 🏆 Key Learning Outcomes & Code Highlights

### 1. Generic Programming Excellence

**TableFormatter\<T\> - Zero Code Duplication**

```java
// Before: Separate table formatting for each entity
// After: One generic formatter for all data types
TableFormatter.<Book>builder("ALL BOOKS")
    .withStyle(TableFormatter.Style.LIGHT)
    .addColumn("Title", 20, Book::title)
    .addColumn("Author", 20, Book::author)
    .display(books);
```

### 2. Repository Pattern Implementation

**Generic JDBC Repository**

```java
public abstract class JdbcRepository<T> {
    protected abstract T mapResultSetToEntity(ResultSet rs);
    
    protected List<T> executeQuery(String sql, Object... params) {
        // Generic implementation handles all entity types
    }
}
```

### 3. Command Pattern with Navigation

**Hierarchical Menu System**

```java
public enum AppRoute {
    MainMenu(MainMenu::new),
    BookInventoryMenu(BookInventoryMenu::new),
    AddBookCommand(AddBookCommand::new);
    
    public Command get() { return this.comSupplier.get(); }
}
```

### 4. Modern Java Features

- **Records for Immutable Entities**: `Book(id, title, author, isbn, pages)`
- **Functional Programming**: Method references and lambda expressions
- **Type Safety**: Generic programming eliminates ClassCastException risks

### 5. Professional Error Handling

```java
protected void performAction() throws Exception {
    try {
        // Business logic here
        showMessage("Operation successful!", false);
    } catch (SQLException e) {
        showMessage("Database error: " + e.getMessage(), true);
    }
}
```

## 🎯 Learning Journey Highlights

### What I Mastered

1. **JDBC Fundamentals**: Raw database connectivity without ORM dependencies
2. **Design Patterns**: Practical implementation of enterprise patterns
3. **Clean Architecture**: Proper separation of concerns across layers  
4. **Docker Integration**: Containerized development environment
5. **Generic Programming**: Type-safe, reusable components
6. **Error Management**: Comprehensive exception handling strategies
7. **User Experience**: Intuitive console interface design

### Challenges Overcome

- **Database Connection Management**: Implemented singleton with retry logic
- **Menu Navigation**: Built stack-based navigation system
- **Code Reusability**: Created generic components to eliminate duplication
- **Data Display**: Developed flexible table formatter supporting multiple styles

## 🔮 Future Improvements & Roadmap

### Phase 1: Core Functionality Completion

- [ ] **Complete Book Search**: Implement search by title, author, ISBN
- [ ] **Book Sorting**: Add sorting by various criteria with different algorithms
- [ ] **Enhanced Delete Operations**: Add cascading delete logic
- [ ] **Data Validation**: Strengthen input validation across all operations

### Phase 2: Advanced Features  

- [ ] **Book Issuing System**: Complete transaction management

  ```java
  class BookIssue {
      Member member;
      Book book; 
      LocalDate issueDate;
      LocalDate dueDate;
      BookStatus status;
  }
  ```

- [ ] **Overdue Management**: Automated notifications and fine calculations
- [ ] **Advanced Reporting**: Generate usage statistics and reports
- [ ] **Data Import/Export**: CSV/JSON support for bulk operations

### Phase 3: Architecture Enhancements

- [ ] **Connection Pooling**: Implement HikariCP for production-ready connections
- [ ] **Caching Layer**: Add Redis integration for frequently accessed data
- [ ] **Event System**: Implement observer pattern for audit logging
- [ ] **Configuration Management**: External configuration files for different environments

### Phase 4: Modern Upgrades

- [ ] **REST API Layer**: Spring Boot integration for web services
- [ ] **GraphQL Support**: Flexible query capabilities
- [ ] **Reactive Programming**: Non-blocking database operations
- [ ] **Microservices**: Split into discrete services with proper API boundaries

### Phase 5: DevOps & Production

- [ ] **CI/CD Pipeline**: Automated testing and deployment
- [ ] **Monitoring**: Application metrics and health checks
- [ ] **Security**: Authentication, authorization, and audit trails
- [ ] **Performance**: Query optimization and benchmarking

## 🧪 Development Process & Lessons Learned

### Code Quality Achievements

1. **Zero Code Duplication**: Generic TableFormatter eliminates repeated table creation logic
2. **Type Safety**: Extensive use of generics prevents runtime ClassCastException
3. **Clean Separation**: Domain entities are independent of UI and database concerns
4. **Professional UX**: Consistent error messaging and user feedback throughout
5. **Resource Management**: Proper database connection handling with singleton pattern



## Code Style Guidelines

- Follow Java naming conventions (camelCase, PascalCase)
- Use meaningful variable and method names
- Add comments for complex business logic
- Maintain consistent error handling patterns
- Write comprehensive commit messages


# About the Author

This project is the result of my hands-on exploration into enterprise Java development, focusing on real-world application of design patterns, clean architecture, and professional coding practices. While the system is a work in progress, it serves as a robust foundation for anyone looking to deepen their understanding of modern Java and software engineering.

*Created and maintained by Rahul Sharma.*

**⭐ Star this repository if it helped in your Java learning journey!**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for complete details.
