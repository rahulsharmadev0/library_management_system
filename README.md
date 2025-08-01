# Library Management System

A modern and robust command-line based Library Management System built in Java with PostgreSQL database integration for efficient library resource management.

## 📑 Overview

This Library Management System provides a comprehensive solution for managing library operations including book inventory, member management, and book issuing functionality. The application uses JDBC for database connectivity, ensuring data persistence and reliability.

## ✨ Features

### 📚 Book Inventory Management
- ✅ Add new books with title, author, ISBN, and page count
- ✅ Update existing book information
- ✅ Delete books from the library database
- ✅ List all books with formatted table display
- ✅ Search books by various criteria
- ✅ Sort book collections by different attributes

### 👥 Member Management
- ✅ Register new library members with contact information
- ✅ Update member details
- ✅ Delete member records
- ✅ Advanced search capability (by ID, name, email, phone)
- ✅ Display member listings in formatted tables

### 📖 Book Issuing System (In Development)
- 🔄 Issue books to registered members
- 🔄 Track book returns
- 🔄 Monitor due dates
- 🔄 Manage overdue notifications

## 🏗️ Architecture

The project employs a clean, layered architecture:

- **Domain Layer**: Contains business entities and core logic
- **Repository Layer**: Handles data access via JDBC
- **Service Layer**: Manages business operations
- **UI Layer**: Provides user interface and command handling

## 🧩 Design Patterns Implemented

- **Singleton Pattern**: For database connection management
- **Repository Pattern**: For data access abstraction
- **Command Pattern**: For encapsulating user actions
- **Factory Pattern**: For object creation
- **Strategy Pattern**: For implementing different search algorithms

## 🔧 Technology Stack

- **Language**: Java
- **Database**: PostgreSQL (with JDBC)
- **UI**: Command-line interface with formatted text tables
- **Build Tool**: Manual compilation (VS Code tasks)

## 📁 Project Structure

```
library_management_system/
├── src/
│   ├── Main.java                    # Entry point
│   ├── domain/
│   │   ├── entities/                # Data models
│   │   │   ├── Book.java
│   │   │   ├── Member.java
│   │   │   └── Option.java
│   │   ├── repositories/            # Data access layer
│   │   │   ├── BookRepository.java
│   │   │   ├── JdbcRepository.java
│   │   │   └── MemberRepository.java
│   │   └── services/                # Business logic
│   │       └── DataBaseManager.java
│   ├── ui/                          # User interface
│   │   ├── AppRoute.java            # Navigation routing
│   │   ├── Navigator.java           # Screen navigator
│   │   ├── action/                  # Command implementations
│   │   │   ├── book/                # Book-related commands
│   │   │   └── member/              # Member-related commands
│   │   └── command/                 # Command framework
│   └── utils/                       # Utility classes
│       └── TableFormatter.java      # Table display formatting
├── bin/                             # Compiled classes
├── lib/                             # External libraries
│   └── postgresql-42.7.7.jar        # PostgreSQL JDBC driver
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- PostgreSQL database
- Command line terminal

### Database Setup
1. Install PostgreSQL on your system
2. Create a database named `LMS`
3. Create the required tables:

```sql
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    pages VARCHAR(10)
);

CREATE TABLE member (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20)
);
```

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/rahulsharmadev0/library_management_system.git
   cd library_management_system
   ```

2. Compile the project:
   ```bash
   javac -cp src:lib/postgresql-42.7.7.jar -d bin src/**/*.java
   ```

3. Run the application:
   ```bash
   java -cp bin:lib/postgresql-42.7.7.jar Main
   ```

### Using VS Code Tasks

A VS Code task is configured for easy compilation:

1. Press `Cmd+Shift+P` (macOS) or `Ctrl+Shift+P` (Windows/Linux)
2. Select `Tasks: Run Task`
3. Choose `Compile Java Project`

## 🖥️ Usage

When you run the application, navigate through the following menus:

1. **Book Inventory Management**
   - Add, update, and delete books
   - Search and sort book collections

2. **Member Management**
   - Register and manage library members
   - Search for members

3. **Book Issuing & Returning**
   - Manage book loans and returns (coming soon)

## 🛠️ Core Components

### Database Connection
The `DataBaseManager` class provides a singleton instance for database connectivity, ensuring efficient connection pooling.

### Repositories
- `JdbcRepository`: Generic base class for database operations
- `BookRepository`: Handles book-related database operations
- `MemberRepository`: Manages member data operations

### UI Framework
- `Navigator`: Manages application flow and screen transitions
- `AppRoute`: Defines all available routes in the application
- `Command`: Base interface for all user actions

## 🔜 Future Enhancements

- [ ] Implement book issuing and return functionality
- [ ] Add user authentication and role-based access
- [ ] Create transaction history and reporting
- [ ] Develop a graphical user interface
- [ ] Implement connection pooling for better performance
- [ ] Add comprehensive logging
- [ ] Create database migration tools

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Rahul Sharma**

## 🙏 Acknowledgments

- Built as a demonstration of Java JDBC implementation
- Utilizes design patterns for maintainable and extensible code
- Showcases clean architecture principles in practice
