# Library Management System

A comprehensive command-line based Library Management System built in Java that provides functionality for managing books, members, and book issuing operations.

## Features

### 📚 Book Inventory Management
- ✅ Add new books to the library
- ✅ Delete books from inventory
- ✅ Update book details
- ✅ Search books by title, author, or ID
- ✅ List all books in the library
- ✅ Sort books by title, author, or publication year

### 👥 Member/User Management
- ✅ Register new library members
- ✅ Update member information
- ✅ Delete member records
- ✅ Search members by ID, name, email, or phone
- ✅ List all registered members

### 📖 Book Issuing & Returning (In Development)
- 🔄 Issue books to members
- 🔄 Return books to the library
- 🔄 Track issue and return dates
- 🔄 Prevent issuing already issued books

## Design Patterns Used

This project demonstrates the implementation of several design patterns:

- **Singleton Design Pattern** - For managing single instances of services
- **Factory Method Design Pattern** - For creating objects without specifying exact classes
- **Command Design Pattern** - For encapsulating requests as objects
- **Strategy Design Pattern** - For defining family of algorithms
- **Generic Class Design Pattern** - For type-safe collections and operations

## Project Structure

```
library_management_system/
├── src/
│   ├── Main.java                    # Application entry point
│   ├── domain/
│   │   ├── entities/                # Data models
│   │   ├── repositories/            # Data access layer
│   │   └── services/                # Business logic layer
│   ├── ui/                          # User interface layer
│   │   ├── action/                  # Command implementations
│   │   └── command/                 # Command framework
│   └── utils/                       # Utility classes
├── bin/                             # Compiled classes
├── book.dat                         # Book data storage
├── member.dat                       # Member data storage
└── README.md
```

## Getting Started

### Prerequisites
- Java 8 or higher
- Command line terminal

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd library_management_system
   ```

2. Compile the project:
   ```bash
   javac -cp src -d bin src/**/*.java
   ```

3. Run the application:
   ```bash
   java -cp bin Main
   ```

### Using VS Code Tasks

If you're using VS Code, you can use the predefined task to compile the project:

1. Open the Command Palette (`Ctrl+Shift+P` or `Cmd+Shift+P`)
2. Run `Tasks: Run Task`
3. Select `Compile Java Project`

## Usage

Upon running the application, you'll be presented with a main menu offering the following options:

1. **Book Inventory Management**
   - Manage your library's book collection
   - Add, update, delete, and search for books

2. **Member Management**
   - Handle library member operations
   - Register, update, and manage member information

3. **Book Issuing & Returning** (Coming Soon)
   - Issue books to members
   - Track and manage book returns

## Key Components

### Core Entities
- **Book**: Represents a book with ISBN, title, author, and other details
- **Member**: Represents a library member with personal information
- **Option**: Generic option class for menu selections

### Services
- **LocalDB**: Generic file-based database service
- **BookService**: Business logic for book operations
- **MemberService**: Business logic for member operations

### UI Framework
- **Navigator**: Handles application navigation
- **Command Pattern**: Encapsulates all user actions
- **TableFormatter**: Generic table formatting utility

## Data Storage

The application uses CSV-based file storage:
- `book.dat` - Stores book information
- `member.dat` - Stores member information

Data is automatically persisted when modifications are made.

## Development Roadmap

- [ ] Complete Book Issuing & Returning functionality
- [ ] Implement automation/code generation via annotations
- [ ] Optimize command package structure
- [ ] Add comprehensive unit tests
- [ ] Implement data validation and error handling
- [ ] Add configuration management


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**Rahul Sharma**

## Acknowledgments

- Built as a demonstration of object-oriented programming principles
- Implements multiple design patterns for educational purposes
- Uses clean architecture principles for maintainable code
