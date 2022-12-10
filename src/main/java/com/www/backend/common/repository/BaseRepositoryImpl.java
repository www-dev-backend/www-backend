package com.www.backend.common.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    protected EntityManager em;
    protected JPAQueryFactory query;
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Autowired
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em, DataSource dataSource) {
        super(domainClass, em);
        this.em = em;
        this.query = new JPAQueryFactory(em);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
