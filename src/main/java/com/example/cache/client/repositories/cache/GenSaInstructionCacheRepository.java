package com.example.cache.client.repositories.cache;

import com.example.cache.client.repositories.view.GenSaInstructionCacheView;
import com.telkomsigma.eclears.app.common.entity.gen.GenSaInstructionEntity;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CachePostfix;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Library Mode / Embedded Cache Repository
 */
@CacheRepository(cacheName = "GEN_SA_INSTRUCTION", entityClass = GenSaInstructionEntity.class, remoteCache = false)
@NoRepositoryBean
public interface GenSaInstructionCacheRepository extends IBaseCacheRepository<Long, GenSaInstructionEntity> {

    List<GenSaInstructionEntity> selectAll(@CachePostfix String uploadId);

    void save(GenSaInstructionEntity data, @CachePostfix String uploadId);

    GenSaInstructionEntity getById(Long id, @CachePostfix String uploadId);

    @Query(value = "SELECT count(instructionId) " +
            "FROM com.telkomsigma.eclears.app.common.entity.gen.GenSaInstructionEntity")
    GenSaInstructionCacheView count(@CachePostfix String uploadId);

}
