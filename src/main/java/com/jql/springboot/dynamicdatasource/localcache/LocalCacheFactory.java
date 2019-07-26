package com.jql.springboot.dynamicdatasource.localcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class LocalCacheFactory<T,M> {

   /* private M mapper;

    private LoadingCache<String,T> instance = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(1, TimeUnit.MICROSECONDS)
            .removalListener(Object::notify).build(new CacheLoader<String, T>() {
                @Override
                public T load(String key) throws Exception {
                    return mapper;
                }
            });

    public LocalCacheFactory(M mapper) {
        this.mapper = mapper;
    }

    public LoadingCache<String, T> getInstance() {
        return instance;
    }*/
}
