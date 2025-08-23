// TODO: 
    - ✅ Create a generic table formatter to eliminate table creation duplication.
    - ✅ Create a generic service base class
    - ✅ Implemement Generic file system plugin
    - Apply some Automation/Code Generation via @annotations
    - Implement Book Issuing & Returning
    - implement pending featers of book inventory management
    - Need to optmize command pkg


/*
## Use this technique
 * Singleton Design pattern
 * Factory method Design pattern
 * Command Design pattern
 * Stratagy Design pattern
 * Generic Class Design pattern
 */

Library Management System
## Book Inventory Management
* Add a new book
* Delete a book
* Update book details
* Search book by title/author/ID
* List all books
* Sort books by title/author/year 
## Member/User Management
* Register a new member
* Update member info
* Delete a member
* Search member by ID or name
* List all registered members
## Book Issuing & Returning
* Issue a book to a member
* Return a book
* Track issue date & return date
* Prevent issuing if already issued

NEED 
1. Storage Service (csv)
2. Load into appropriate data structure
3. CLI Rendering Service (State)
4. CLI command listener service (Events)
5. CLI executor, (Controller/Logic) who can change state


