package com.krywojciechowski.Shortener.Repository;

import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Component
public class ShortenedUrlDAO implements IShortenedUrlDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ShortenedUrl find(ShortenedUrl predicate) {
        return this.entityManager.find(ShortenedUrl.class,predicate);
    }

    @Override
    public int count(ShortenedUrl shortenedUrl) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<ShortenedUrl> root = query.from(ShortenedUrl.class);
        query.select(criteriaBuilder.count(root));
        Query qr = this.entityManager.unwrap(Session.class).createQuery(query);
        return qr.getFirstResult();
    }

    public void insert(ShortenedUrl shortenedUrl){
        Session session = this.entityManager.unwrap(Session.class);
        session.save(shortenedUrl);
    }


}
