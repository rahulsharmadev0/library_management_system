package domain.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domain.services.DataBaseManager;
import java.io.Closeable;

public abstract class JdbcRepository<T> implements Closeable {
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    protected Connection connection;

    public JdbcRepository() throws SQLException {
        this.connection = DataBaseManager.getInstance().getConnection();
    }
    
    /**
     * Close database resources
     */
    @Override
    public void close() {
        // Connection is managed by DataBaseManager, no need to close here
    }

    public int executeUpdate(String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++)
                ps.setObject(i + 1, params[i]);
            return ps.executeUpdate();
        }
    }

    protected List<T> executeQuery(String sql, Object... params) throws SQLException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++)
                ps.setObject(i + 1, params[i]);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    results.add(mapResultSetToEntity(rs));
            }
        }
        return results;
    }

}
