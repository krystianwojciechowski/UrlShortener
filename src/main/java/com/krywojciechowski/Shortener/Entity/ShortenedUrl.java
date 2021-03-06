package com.krywojciechowski.Shortener.Entity;

import com.krywojciechowski.Shortener.cache.Cacheable.ICacheable;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Entity
@Table(name = "shortened_url")
public class ShortenedUrl implements ICacheable, Serializable {
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

    //returns data that can be seen by user, used to return from controller
    public HashMap<String,String> getSafeData(){
        HashMap<String,String> data = new HashMap<>();
        data.put("url", this.url);
        data.put("shortenedUrl", this.hash);
        return data;
    }
    @Override
    public String getCacheKey() {
        return this.hash;
    }

    @Override
    public String getCacheValue() {
        return this.url;
    }
}
