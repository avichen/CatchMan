package com.roside.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by Eric on 2017-02-21.
 */
@Configuration
@EnableCaching
public class CacheConfig
{
    @Bean
    public SimpleCacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(userCache(),defaultCache()));
        return cacheManager;
    }

    @Bean(name="userCache")
    public ConcurrentMapCache userCache(){
        ConcurrentMapCache userCache = new ConcurrentMapCache("userCache");
        return  userCache;
    }

    @Bean
    public ConcurrentMapCache defaultCache(){
        ConcurrentMapCache defaultCache = new ConcurrentMapCache("defaultCache");
        return  defaultCache;
    }
}