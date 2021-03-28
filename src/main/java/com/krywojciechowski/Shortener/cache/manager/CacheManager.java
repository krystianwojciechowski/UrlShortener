package com.krywojciechowski.Shortener.cache.manager;

import com.krywojciechowski.Shortener.cache.Cacheable.ICacheable;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;


public abstract class CacheManager {

    public enum CacheClearStrategy {
        ALL,
        EXPIRED
    }
    //returns true if element meets requirements to be stored in cache
    public abstract boolean shouldCache();

    public abstract void saveInCache(ICacheable cacheable);

    public abstract String retrieveFromCache(String key);

    public abstract String[] retrieveFromCache(String[] keys) throws ExecutionException, InterruptedException;

    public abstract void clearCache(CacheClearStrategy strategy);

    public abstract void clearCache(ICacheable cacheable);
}
