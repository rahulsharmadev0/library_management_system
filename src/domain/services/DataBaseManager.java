package domain.services;

import java.sql.*;

public class DataBaseManager {
    private static final String URL = "jdbc:postgresql://ep-sparkling-fog-a1kx2q3d-pooler.ap-southeast-1.aws.neon.tech/LMS?sslmode=require&channel_binding=require";
    private static final String USERNAME = "neondb_owner";
    private static final String PASSWORD = "npg_fz5gM1BmuNFA";

    private Connection connection;

    private DataBaseManager() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Connected to PostgreSQL database!");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL driver not found", e);
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
