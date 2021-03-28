package com.krywojciechowski.Shortener.cache.manager;

import com.krywojciechowski.Shortener.cache.Cacheable.ICacheable;
import com.krywojciechowski.Shortener.cache.JedisCacheConnectionFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.thymeleaf.cache.ICacheManager;
import redis.clients.jedis.Jedis;

import javax.persistence.Cacheable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
@Component
public class JedisCacheManager extends CacheManager {


    @Autowired
    private JedisCacheConnectionFactory cacheConnectionFactory;

    @Override
    public boolean shouldCache() {

        //@todo implement cache key lifecycle
        return false;
    }

    @Override
    public void saveInCache(ICacheable cacheable) {
        Jedis connection = this.cacheConnectionFactory.getConnection();
        connection.set(cacheable.getCacheKey(),cacheable.getCacheValue());
    }

    @Override
    public String retrieveFromCache(String key) {
        Jedis connection = this.cacheConnectionFactory.getConnection();
        String value =  connection.get(key);
        connection.close();
        return value;
    }

    @Override
    public String[] retrieveFromCache(String[] keys) throws ExecutionException, InterruptedException {

        int maxConnections = this.cacheConnectionFactory.getMaxConnections();
        String[] additionalResults = null;
        if(keys.length>maxConnections){
                String[] keysSubarray = Arrays.copyOfRange(keys, maxConnections, keys.length-1);
                keys = List.of(keys).subList(0,maxConnections ).toArray(String[]::new);
                additionalResults = this.retrieveFromCache(keysSubarray);
        }

        List<CompletableFuture<String>> futures = new ArrayList<CompletableFuture<String>>();
        for(String key : keys){
            futures.add(new CompletableFuture<String>().supplyAsync(()->{
                return this.retrieveFromCache(key);
            }));
        }

        CompletableFuture<Void> futureCollection = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

        CompletableFuture<String[]> finalFuture = futureCollection.thenApply((future) -> {
            return futures.stream().map(completableFuture -> completableFuture.join()).collect(Collectors.toList()).toArray(String[]::new);
        });

        if(additionalResults == null) {
            return finalFuture.get();
        } else {
            return ArrayUtils.addAll(finalFuture.get(),additionalResults);
        }
    }

    @Override
    public void clearCache(CacheClearStrategy strategy) {
        Jedis connection = this.cacheConnectionFactory.getConnection();
        if(strategy == CacheClearStrategy.ALL){
            connection.flushDB();
        } else if(strategy == CacheClearStrategy.EXPIRED) {
            //@todo implement cache key lifecycle
        }
    }

    @Override
    public void clearCache(ICacheable cacheable) {
        Jedis connection =  this.cacheConnectionFactory.getConnection();
        connection.del(cacheable.getCacheKey());
        connection.close();
    }

}
