package com.example.cache.client.repositories.cache;

import com.telkomsigma.eclears.app.common.entity.gen.GenMdInstrumentEntity;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;

/**
 * Remote cache repository
 */
@CacheRepository(cacheName = "GEN_MD_INSTRUMENT", entityClass = GenMdInstrumentEntity.class)
public interface GenMdInstrumentCacheRepository extends IBaseCacheRepository<Long, GenMdInstrumentEntity> {

}
