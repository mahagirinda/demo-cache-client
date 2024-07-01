package com.example.cache.client.repositories.cache;

import com.telkomsigma.eclears.app.common.entity.gen.GenTxInstrumentExceptionEntity;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;

/**
 * Remote cache repository
 */
@CacheRepository(cacheName = "GEN_TX_INSTRUMENT_EXCEPTION", entityClass = GenTxInstrumentExceptionEntity.class)
public interface GenTxInstrumentExceptionCacheRepository extends IBaseCacheRepository<Long, GenTxInstrumentExceptionEntity> {

}
