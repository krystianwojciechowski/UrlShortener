package com.krywojciechowski.Shortener.Dao;

import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IShortenedUrlDAO {
    public ShortenedUrl find(ShortenedUrl predicate);
    public ShortenedUrl find(int predicate);
    public int count(ShortenedUrl predicate);
    public void insert(ShortenedUrl shortenedUrl);
}
