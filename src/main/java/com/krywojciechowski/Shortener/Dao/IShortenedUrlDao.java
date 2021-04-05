package com.krywojciechowski.Shortener.Dao;

import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IShortenedUrlDao {
    public ShortenedUrl find(ShortenedUrl predicate);
    public ShortenedUrl find(int predicate);
    public ShortenedUrl find(String hash);
    public int count(ShortenedUrl predicate);
    public void insert(ShortenedUrl shortenedUrl);
}
