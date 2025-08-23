-- Library Management System Database Initialization Script

-- Create tables for the library management system
CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL, 
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    pages INTEGER CHECK (pages > 0)
);

CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20)
);

-- Insert some sample data
INSERT INTO book (title, author, isbn, pages) VALUES
    ('The Great Gatsby', 'F. Scott Fitzgerald', '978-0-7432-7356-5', 218),
    ('To Kill a Mockingbird', 'Harper Lee', '978-0-06-112008-4', 281),
    ('1984', 'George Orwell', '978-0-452-28423-4', 328),
    ('Pride and Prejudice', 'Jane Austen', '978-0-14-143951-8', 279),
    ('The Catcher in the Rye', 'J.D. Salinger', '978-0-316-76948-0', 277);

INSERT INTO member (name, email, phone) VALUES
    ('John Doe', 'john.doe@email.com', '+1-234-567-8901'),
    ('Jane Smith', 'jane.smith@email.com', '+1-234-567-8902'),
    ('Bob Johnson', 'bob.johnson@email.com', '+1-234-567-8903');

-- Optionally create the user (uncomment if needed)
-- CREATE USER lms_user WITH PASSWORD 'your_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO lms_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO lms_user;
