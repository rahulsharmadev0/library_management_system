package com.library_management_system.app.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.library_management_system.app.ui.adapters.TableFormatter;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;

    public static Member of(String name, String email, String phone) {
        return new Member(null, name, email, phone);
    }

    @Getter
    private static final TableFormatter<Member> tableFormat;

    static {
        tableFormat = TableFormatter.<Member>create()
                .withIndex(false)
                .addColumn("Member ID", 12, Member::getId)
                .addColumn("Name", 20, Member::getName)
                .addColumn("Email", 20, Member::getEmail)
                .addColumn("Phone", 15, Member::getPhone);
    }

}
