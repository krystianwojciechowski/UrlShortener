package com.krywojciechowski.Shortener.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visit_history")
public class VisitHistory {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @Column
    @ManyToOne
    private ShortenedUrl shortenedUrl;
    @Column
    private Date visitedAt;

    public VisitHistory(ShortenedUrl shortenedUrl, Date visitedAt) {
        this.shortenedUrl = shortenedUrl;
        this.visitedAt = visitedAt;
    }

    public int getId() {
        return id;
    }

    public ShortenedUrl getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(ShortenedUrl shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Date visitedAt) {
        this.visitedAt = visitedAt;
    }
}
