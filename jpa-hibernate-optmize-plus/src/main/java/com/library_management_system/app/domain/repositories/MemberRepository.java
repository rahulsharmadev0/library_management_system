package com.library_management_system.app.domain.repositories;

import java.util.List;
import java.util.Optional;
import com.library_management_system.app.domain.entities.Member;
import com.library_management_system.app.domain.repositories.JpaRepository;

public class MemberRepository extends JpaRepository {
    private static MemberRepository instance;

    private MemberRepository() {
        super();
    }

    public static MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    public void insert(Member entity) {
        executeWithTransaction(emf -> {
            emf.persist(entity);
        });
    }

    public void update(Member entity) {
        executeWithTransaction(emf -> {
            emf.merge(entity);
        });
    }

    public void delete(int id) {
        executeWithTransaction(emf -> {
            Member member = emf.find(Member.class, id);
            if (member != null) {
                emf.remove(member);
            }
        });
    }

    public List<Member> findByName(String name) {
        return execute(emf -> {
            return emf.createQuery("SELECT m FROM Member m WHERE m.name LIKE :name", Member.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        });
    }

    public List<Member> findByEmail(String email) {
        return execute(emf -> {
            return emf.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getResultList();
        });
    }

    public List<Member> findByPhone(String phone) {
        return execute(emf -> {
            return emf.createQuery("SELECT m FROM Member m WHERE m.phone = :phone", Member.class)
                    .setParameter("phone", phone)
                    .getResultList();
        });
    }

    public Optional<Member> findById(int id) {
        return execute(emf -> {
            Member member = emf.find(Member.class, id);
            return Optional.ofNullable(member);
        });
    }

    public List<Member> findAll() {
        return execute(emf -> {
            return emf.createQuery("SELECT m FROM Member m", Member.class)
                    .getResultList();
        });
    }

    public boolean existsById(int id) {
        return execute(emf -> {
            Member member = emf.find(Member.class, id);
            return member != null;
        });
    }
}
