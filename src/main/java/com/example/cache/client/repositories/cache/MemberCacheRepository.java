package com.example.cache.client.repositories.cache;

import com.example.cache.client.view.MemberView;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdMemberEntity;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Param;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.cache.client.AppConstant.JDG.CACHE.GEN_MD_MEMBER;

/**
 * Remote cache repository
 */
@CacheRepository(cacheName = GEN_MD_MEMBER, entityClass = GenMdMemberEntity.class)
public interface MemberCacheRepository extends IBaseCacheRepository<Long, GenMdMemberEntity> {

    @Query("from GenMdMemberEntity where memberId = :memberId")
    GenMdMemberEntity getByMemberId(@Param Long memberId);

    @Query("from GenMdMemberEntity where memberCode = :memberCode")
    GenMdMemberEntity getByMemberCode(@Param String memberCode);

    List<GenMdAccountEntity> selectAll(Pageable pageable);

    @Query("select memberId, memberStatus from GenMdMemberEntity where memberCode = :memberCode")
    MemberView getMemberSimpleByMemberCode(@Param String memberCode);

    @Query("select memberCode from GenMdMemberEntity where memberId = :memberId")
    List<Object> getMemberCodeByMemberId(@Param Long memberId);
}
