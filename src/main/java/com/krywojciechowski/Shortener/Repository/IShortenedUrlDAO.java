package com.krywojciechowski.Shortener.Repository;

import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import org.springframework.stereotype.Component;


public interface IShortenedUrlDAO {
    public ShortenedUrl find(ShortenedUrl predicate);
    public int count(ShortenedUrl predicate);
    public void insert(ShortenedUrl shortenedUrl);
}
