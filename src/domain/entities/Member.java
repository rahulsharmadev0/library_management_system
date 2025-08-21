package domain.entities;

import utils.TableFormatter;

public record Member(int id, String name, String email, String phone) {

    private static final TableFormatter<Member> tableFormat = TableFormatter.<Member>create()
            .addColumn("Member ID", 12, Member::id)
            .addColumn("Name", 20, Member::name)
            .addColumn("Email", 20, Member::email)
            .addColumn("Phone", 15, Member::phone);

    public static TableFormatter<Member> getTableFormat() {
        return tableFormat;
    }

}
