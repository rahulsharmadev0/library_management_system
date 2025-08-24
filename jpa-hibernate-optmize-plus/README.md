# Library Management System

A modern console-based Library Management System built with Java and PostgreSQL, demonstrating clean architecture principles, design patterns, and professional software development practices.

## 📑 Project Overview

This Library Management System is a learning-focused project that showcases the implementation of enterprise-level Java applications with database integration. The project demonstrates modern software engineering concepts including clean architecture, design patterns, JPA (Jakarta Persistence) with Hibernate, and Docker containerization.

**🎯 Learning Objectives:**

- Master JPA (Jakarta Persistence) with Hibernate and transaction management
- Implement enterprise design patterns (Singleton, Repository, Command, Factory)
- Practice clean architecture and separation of concerns
- Learn Docker containerization for database services
- Build reusable and maintainable console applications with a CLI UI

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
- ✅ **Generic Repository Pattern**: Reusable JPA operations
- ✅ **Table Formatter**: Generic utility for consistent data display
- ✅ **Command Pattern**: Extensible command system for UI actions

### 🕒 Not Yet Implemented

- ❌ Book Issuing & Returning workflow (placeholders only)
- ❌ Overdue management and fines
- ❌ Advanced reporting and analytics
- ❌ Data import/export (CSV/JSON)

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

- **Java 17**: LTS runtime specified in Maven compiler settings
- **Jakarta Persistence (JPA) + Hibernate**: ORM for entity mapping and transactions
- **PostgreSQL**: Backed by dockerized database with init.sql seeding
- **Docker Compose**: Reproducible database environment
- **Maven**: Build and dependency management
- **Lombok**: Boilerplate reduction for entities

### Key Learning Areas

- **Database Design**: Proper normalization, constraints, and indexing
- **Persistence & Transactions**: EntityManager lifecycle and transaction boundaries via helper methods
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

## 🔧 Optimization Analysis (2025-08-23)

### Implemented Optimizations
- JPA-based repository helpers centralize EntityManager and transaction boundaries.
- Consistent, reusable TableFormatter for domain entities (Book, Member) to avoid duplication.
- Command pattern with ActionCommand/MenuViewCommand enforces uniform UX and error messaging.
- Dockerized PostgreSQL with init.sql provides reproducible local environment.

### Identified Improvement Opportunities
- Remove duplicate persistence.xml under src/main/java (keep only in src/main/resources/META-INF).
- Unify input handling: avoid multiple Scanner instances on System.in (reuse Input.get()).
- Replace recursive menu re-entry with iterative loops to avoid stack growth.
- Close EntityManagerFactory on shutdown with a JVM shutdown hook.
- Add validation annotations and input validation at service boundaries.
- Introduce tests (H2 for repositories; simulate CLI input for menus) and basic CI.
- Decouple entity formatting from domain (move TableFormatter configs to UI presenters).

## 📁 Project Structure (simplified)

```
src/main/java/com/library_management_system/app/
├── LmsApplication.java
├── domain/
│   ├── entities/
│   │   ├── Book.java
│   │   ├── Member.java
│   │   └── BookIssue.java
│   └── repositories/
│       ├── JpaRepository.java
│       ├── BookRepository.java
│       └── MemberRepository.java
├── ui/
│   ├── AppRoute.java
│   ├── MainMenu.java
│   ├── Navigator.java
│   ├── adapters/
│   │   ├── Input.java
│   │   └── TableFormatter.java
│   ├── adapters/commands/
│   │   ├── Command.java
│   │   └── ActionCommand.java
│   ├── utils/
│   │   └── MenuViewCommand.java
│   ├── book_inventory_management/...
│   └── member_management/...
src/main/resources/
├── META-INF/persistence.xml
└── application.properties

docker-compose.yml
init.sql
pom.xml
Makefile
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
   # Start DB (first time)
   docker-compose up -d postgresql

   # Build and run
   ./mvnw clean package -q
   java -jar target/app-0.0.1-SNAPSHOT.jar
   
   # Or use Makefile helper
   make run
   ```

### Compile, Run, and Debug

- Compile only:
  - make compile-project
  - or: ./mvnw -q -DskipTests package

- Run the app:
  - make run
  - or: java -jar target/app-0.0.1-SNAPSHOT.jar

- Debug the app (JDWP on port 5005):
  - make debug  # starts the app with a debug agent on port 5005
  - In your IDE, attach a remote debugger to localhost:5005 (Java 17)
  - To start suspended (wait for debugger before running), edit Makefile debug target to set suspend=y

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

```
// Generic formatter reused across entities
TableFormatter.<Book>create()
    .withIndex(false)
    .addColumn("Title", 20, Book::getTitle)
    .addColumn("Author", 20, Book::getAuthor)
    .addColumn("ISBN", 14, Book::getIsbn)
    .addColumn("Pages", 6, Book::getPages)
    .display(books);
```

### 2. Repository Pattern Implementation

**JPA Repository Helpers**

```
public abstract class JpaRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lms-unit");

    protected <R> R execute(Function<EntityManager, R> fun) {
        try (var em = emf.createEntityManager()) {
            return fun.apply(em);
        }
    }

    protected void executeWithTransaction(Consumer<EntityManager> fun) {
        try (var em = emf.createEntityManager()) {
            var tx = em.getTransaction();
            try {
                tx.begin();
                fun.accept(em);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw new RuntimeException(e);
            }
        }
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

- **Lombok** for getters/constructors to reduce boilerplate
- **Functional Programming**: Method references and stream operations
- **Records**: Lightweight Option record for menu options
- **Type Safety**: Generics in repositories and table formatting

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
