package com.krywojciechowski.Shortener.cache;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.Jedis;

public interface ICacheConnectionFactory {
    public Jedis getConnection();
    public int getMaxConnections();
}
