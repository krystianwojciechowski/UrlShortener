package com.krywojciechowski.Shortener.Dao;

import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import com.krywojciechowski.Shortener.Entity.VisitHistory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Component
public class VisitHistoryDao implements IVisitHistoryDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<VisitHistory> findAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<VisitHistory> criteriaQuery = criteriaBuilder.createQuery(VisitHistory.class);
        Root<VisitHistory> root = criteriaQuery.from(VisitHistory.class);
        criteriaQuery.select(root);
        return this.entityManager.unwrap(Session.class).createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<VisitHistory> find(Date start, Date end) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<VisitHistory> criteriaQuery = criteriaBuilder.createQuery(VisitHistory.class);
        Root<VisitHistory> root = criteriaQuery.from(VisitHistory.class);
        criteriaQuery.where(criteriaBuilder.between(root.get("visitedAt"), start, end));
        criteriaQuery.select(root);
        return this.entityManager.unwrap(Session.class).createQuery(criteriaQuery).getResultList();
    }

    public void insert(VisitHistory visitHistory){
        Session session = this.entityManager.unwrap(Session.class);
        session.save(visitHistory);
    }
}
