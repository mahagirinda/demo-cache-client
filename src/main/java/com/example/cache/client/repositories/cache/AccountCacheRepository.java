package com.example.cache.client.repositories.cache;

import com.example.cache.client.view.AccountView;
import com.example.cache.client.view.CountAccountView;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Param;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.cache.client.AppConstant.JDG.CACHE.GEN_MD_ACCOUNT;

/**
 * Remote cache repository
 */
@CacheRepository(cacheName = GEN_MD_ACCOUNT, entityClass = GenMdAccountEntity.class)
public interface AccountCacheRepository extends IBaseCacheRepository<Long, GenMdAccountEntity> {
    @Query("from GenMdAccountEntity where memberId = :memberId and clientCode = :clientCode and accountType = :accountType")
    List<GenMdAccountEntity> getByAccountByMemberIdAndClientCodeAndAccountType(
            @Param Long memberId,
            @Param String clientCode,
            @Param String accountType
    );

    @Query("select accountId, accountCode, accountType, memberId, accountStatus, clientCode " +
            "from GenMdAccountEntity " +
            "where memberId = :memberId and clientCode = :clientCode and accountType = :accountType")
    List<AccountView> getByAccountByMemberIdAndClientCodeAndAccountTypeSimple(
            @Param Long memberId,
            @Param String clientCode,
            @Param String accountType
    );

    @Query("from GenMdAccountEntity where accountId = :accountId")
    GenMdAccountEntity getByAccountId(@Param("accountId") Long accountId);

    @Query("from GenMdAccountEntity where accountCode = :accountCode")
    GenMdAccountEntity getByAccountCode(@Param("accountCode") String accountCode);

    @Query("SELECT count(accountId) FROM com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity")
    AccountView dataCount();

    List<GenMdAccountEntity> selectAll(Pageable pageable);
}
