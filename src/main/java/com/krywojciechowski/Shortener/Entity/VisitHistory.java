package com.krywojciechowski.Shortener.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visit_history")
public class VisitHistory {

    @Column
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String hash;
    @CreationTimestamp
    @Column
    private Date visitedAt;

    public VisitHistory(String hash, Date visitedAt) {
        this.hash = hash;
        this.visitedAt = visitedAt;
    }

    public int getId() {
        return id;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String  hash) {
        this.hash = hash;
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Date visitedAt) {
        this.visitedAt = visitedAt;
    }
}
