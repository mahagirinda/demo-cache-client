package com.example.cache.client.repositories.cache;

import com.example.cache.client.view.CountAccountView;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxInstructionEntity;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CachePostfix;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;

import java.util.List;

import static com.example.cache.client.AppConstant.JDG.CACHE.GEN_TX_INSTRUCTION;

/**
 * Remote Cache
 */
@CacheRepository(cacheName = GEN_TX_INSTRUCTION, entityClass = GenTxInstructionEntity.class)
public interface InstructionCacheRepository extends IBaseCacheRepository<Long, GenTxInstructionEntity> {

    List<GenTxInstructionEntity> selectAll(@CachePostfix String cachePostFix);

    GenTxInstructionEntity getById(Long id, @CachePostfix String cachePostFix);

    void save(GenTxInstructionEntity data, @CachePostfix String cachePostFix);

    void remove(Long instructionId, @CachePostfix String cachePostFix);

    @Query("select count(*) from GenTxInstructionEntity")
    CountAccountView count();
}
