package domain.repositories;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain.entities.Member;

public class MemberRepository extends JdbcRepository<Member>{
    
    private static MemberRepository instance;
    
    public static MemberRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    private MemberRepository() throws SQLException {
        super();
    }

    @Override
    protected Member mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Member(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("phone")
        );
    }

    private static final String TABLE_NAME = "member";

    public List<Member> getAll() throws SQLException {
        return executeQuery("SELECT * FROM " + TABLE_NAME);
    }

    public int insert(Member entity) throws SQLException {
        return executeUpdate(
                "INSERT INTO " + TABLE_NAME + " (name, email, phone) VALUES (?, ?, ?)",
                entity.name(), entity.email(), entity.phone());
    }

    public int update(Member entity) throws SQLException {
        return executeUpdate(
                "UPDATE " + TABLE_NAME + " SET name = ?, email = ?, phone = ? WHERE id = ?",
                entity.name(), entity.email(), entity.phone(), entity.id());
    }

    public int delete(int id) throws SQLException {
        return executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE id = ?", id);
    }
    
    public Member findById(int id) throws SQLException {
        List<Member> results = executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = ?", id);
        return results.isEmpty() ? null : results.get(0);
    }
}
