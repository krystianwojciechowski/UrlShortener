package com.krywojciechowski.Shortener.Dao;

import com.krywojciechowski.Shortener.Entity.VisitHistory;

import java.util.Date;
import java.util.List;

public interface IVisitHistoryDao {
    public List<VisitHistory> findAll();
    public List<VisitHistory> find(Date start,Date end);
    public void insert(VisitHistory visitHistory);
}
