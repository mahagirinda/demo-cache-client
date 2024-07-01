package com.example.cache.client.services;


import com.example.cache.client.jdg.JdgFilterHelper;

import java.util.List;
import java.util.Map;

public interface JdgRestService {
    void addObject(String cacheName, String key, Object object);

    void addAllObject(String cacheName, Map<String, ?> objects);

    void saveObject(String cacheName, String key, Object object);

    void deleteObject(String cacheName, String key);

    <E> E getObject(String cacheName, String key, Class<E> classType);

    List<String> getKeySet(String cacheName);

    <E> List<E> getBySearchCriteria(String cacheName, JdgFilterHelper filter);

    void clear(String cacheName);

    Integer countCache(String cacheName);

    void removeCache(String cacheName);
}
