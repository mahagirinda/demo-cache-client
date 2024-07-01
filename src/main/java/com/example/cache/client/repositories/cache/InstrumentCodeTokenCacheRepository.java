package com.example.cache.client.repositories.cache;

import com.example.cache.client.view.StringCacheView;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Library Mode / Embedded Cache Repository
 */
@CacheRepository(cacheName = "INSTR_CODE_TOKEN_CALC_PRICE", entityClass = String.class, remoteCache = false)
@NoRepositoryBean
public interface InstrumentCodeTokenCacheRepository extends IBaseCacheRepository<String, String> {

    @Query("select count() from INSTR_CODE_TOKEN_CALC_PRICE")
    StringCacheView count();

}
