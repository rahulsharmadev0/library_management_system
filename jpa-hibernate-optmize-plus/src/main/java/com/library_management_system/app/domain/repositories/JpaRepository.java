package com.library_management_system.app.domain.repositories;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import jakarta.persistence.EntityManager;

public abstract class JpaRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lms-unit");

    protected <R> R execute(Function<EntityManager, R> fun) {
        try (var em = emf.createEntityManager()) {
            return fun.apply(em);
        }
    }

    protected <R> R executeWithTransaction(Function<EntityManager, R> fun) {
        try (var em = emf.createEntityManager()) {
            var tx = em.getTransaction();
            try {
                tx.begin();
                R result = fun.apply(em);
                tx.commit();
                return result;
            } catch (Exception e) {
                if (tx.isActive())
                    tx.rollback();
                throw new RuntimeException(e);
            }
        }
    }
    protected  void executeWithTransaction(Consumer<EntityManager> fun) {
        try (var em = emf.createEntityManager()) {
            var tx = em.getTransaction();
            try {
                tx.begin();
                fun.accept(em);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive())
                    tx.rollback();
                throw new RuntimeException(e);
            }
        }
    }

}