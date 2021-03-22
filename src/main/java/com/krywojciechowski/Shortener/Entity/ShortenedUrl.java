package com.krywojciechowski.Shortener.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shortened_url")
public class ShortenedUrl {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "hash")
    private String hash;
    @Column(name = "url")
    private String url;
    @CreationTimestamp
    @Column
    private Date createdAt;

    public ShortenedUrl(){ }

    public ShortenedUrl(String hash, String url, Date createdAt) {
        this.hash = hash;
        this.url = url;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
