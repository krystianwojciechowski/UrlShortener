package com.krywojciechowski.Shortener.Dao;

import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Component
public class ShortenedUrlDAO implements IShortenedUrlDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ShortenedUrl find(int predicate) {
        return this.entityManager.find(ShortenedUrl.class,predicate);
    }

    //@todo consider adding more overriding methods that take url,hash and creation date as arguments
    @Override
    public ShortenedUrl find(ShortenedUrl predicate){
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ShortenedUrl> query = criteriaBuilder.createQuery(ShortenedUrl.class);
        Root<ShortenedUrl> root = query.from(ShortenedUrl.class);
        query.where(criteriaBuilder.equal(root.get("url"), predicate.getUrl()));
        query.where(criteriaBuilder.equal(root.get("hash"), predicate.getHash()));
        query.select(root);
        Query qr = this.entityManager.unwrap(Session.class).createQuery(query);
        return (ShortenedUrl) qr.getSingleResult();
    }

    @Override
    public ShortenedUrl find(String hash){
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ShortenedUrl> query = criteriaBuilder.createQuery(ShortenedUrl.class);
        Root<ShortenedUrl> root = query.from(ShortenedUrl.class);
        query.where(criteriaBuilder.equal(root.get("hash"), hash));
        query.select(root);
        Query qr = this.entityManager.unwrap(Session.class).createQuery(query);
         try {
           return (ShortenedUrl) qr.getSingleResult();
         } catch (NoResultException exception){
             return null;
         }
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
