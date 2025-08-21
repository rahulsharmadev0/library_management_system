package domain.services;
import java.sql.*;

public class DataBaseManager {
    // Local Docker PostgreSQL configuration
    private static final String URL = "jdbc:postgresql://localhost:5432/library_management_system";
    private static final String USERNAME = "lms_user";
    private static final String PASSWORD = "lms_password";

    private Connection connection;

    private DataBaseManager() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Connected to PostgreSQL database!");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL driver not found. Make sure postgresql-42.7.7.jar is in the classpath.", e);
        }
    }

    private static DataBaseManager instance;

    public static DataBaseManager getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
