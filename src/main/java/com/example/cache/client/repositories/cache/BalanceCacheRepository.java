package com.example.cache.client.repositories.cache;

import com.example.cache.client.AppConstant;
import com.example.cache.client.view.CountAccountView;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

/**
 * Library Mode / Embedded Cache Repository
 */
@CacheRepository(cacheName = AppConstant.JDG.CACHE.BALANCE, entityClass = Map.class, remoteCache = false)
@NoRepositoryBean
public interface BalanceCacheRepository extends IBaseCacheRepository<String, Map> {

}
