package com.example.cache.client.repositories.cache;

import com.example.cache.client.views.GenSaAcctBalanceReqView;
import com.telkomsigma.eclears.app.common.entity.gen.GenSaAcctBalanceReqEntity;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Param;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import org.springframework.data.repository.NoRepositoryBean;

@CacheRepository(cacheName = "GEN_SA_ACCT_BALANCE_REQ", entityClass = GenSaAcctBalanceReqEntity.class, remoteCache = false)
@NoRepositoryBean
public interface GenSaAcctBalanceReqCacheRepository extends IBaseCacheRepository<Long, GenSaAcctBalanceReqEntity> {
    @Query("from GenSaAcctBalanceReqEntity " +
            "where accountId = :accountId")
    GenSaAcctBalanceReqEntity getByAccountId(@Param("accountId") Long accountId);

    @Query("select count(accountId) from com.telkomsigma.eclears.app.common.entity.gen.GenSaAcctBalanceReqEntity")
    GenSaAcctBalanceReqView count();
}
