package com.krywojciechowski.Shortener.cache;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class CacheManager implements ICacheManager{

    private JedisPoolConfig config;
    private JedisPool connectionPool;

    private JedisPoolConfig configure(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(256);
        config.setMaxTotal(256);
        config.setMinIdle(32);
        return config;
    }

    @Override
    public Jedis getConnection() {
        if(this.config == null){
            this.config = this.configure();
            this.connectionPool = new JedisPool(config,"127.0.0.1"); //@todo add server in config file
        }
        return this.connectionPool.getResource();
    }
}
