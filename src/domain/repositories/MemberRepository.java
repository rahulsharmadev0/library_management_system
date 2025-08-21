package domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import domain.entities.Member;

public class MemberRepository extends JdbcRepository<Member> {

    public static final String TABLE_NAME = "member";
    private static MemberRepository instance;

    private MemberRepository() throws SQLException {
        super();
    }

    public static MemberRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Member mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Member(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone"));
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

    public List<Member> findByName(String name) throws SQLException {
        return executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE name LIKE ?", "%" + name + "%");
    }

    public List<Member> findByEmail(String email) throws SQLException {
        return executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = ?", email);
    }

    public List<Member> findByPhone(String phone) throws SQLException {
        return executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE phone = ?", phone);
    }
}
