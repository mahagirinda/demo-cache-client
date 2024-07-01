package com.example.cache.client.repositories.cache;

import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Library Mode / Embedded Cache Repository
 */
@CacheRepository(cacheName = "PUBSUB_TRADEMGMT", entityClass = HashMap.class, remoteCache = false)
@NoRepositoryBean
public interface PubsubTradeManagementCacheRepository extends IBaseCacheRepository<String, HashMap<Object, Object>> {

}
