package com.example.cache.client.repositories.cache;

import com.example.cache.client.view.CountUploadView;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CachePostfix;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import com.telkomsigma.framework.core.api.uploadfile.entity.GenTxUpload;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

import static com.example.cache.client.AppConstant.JDG.CACHE.GEN_TX_UPLOAD;

/**
 * Library Mode / Embedded Cache Repository
 */
@CacheRepository(cacheName = GEN_TX_UPLOAD, entityClass = GenTxUpload.class, remoteCache = false)
@NoRepositoryBean
public interface UploadCacheRepository extends IBaseCacheRepository<Long, GenTxUpload> {

    List<GenTxUpload> selectAll(@CachePostfix String cachePostFix);

    GenTxUpload getById(Long id, @CachePostfix String cachePostFix);

    void save(GenTxUpload data, @CachePostfix String cachePostFix);

    void remove(Long id, @CachePostfix String cachePostFix);

    @Query("select count(uploadId) from com.telkomsigma.framework.core.api.uploadfile.entity.GenTxUpload")
    CountUploadView count();

}
