package com.example.cache.client.repositories;

import com.telkomsigma.eclears.app.common.entity.gen.GenSaAcctBalanceReqEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenSaAcctBalanceReqRepo extends PagingAndSortingRepository<GenSaAcctBalanceReqEntity, Long> {

	List<GenSaAcctBalanceReqEntity> findBybpmProcessId(String p_BpmProcessId);

	GenSaAcctBalanceReqEntity findBybpmProcessIdLike(String p_BpmProcessId);

	GenSaAcctBalanceReqEntity findByAccountId(Long p_AccountId);

	GenSaAcctBalanceReqEntity findByAccountIdAndDataStatus(Long p_AccountId, Character p_DataStatus);

	GenSaAcctBalanceReqEntity findByRequestId(Long p_RequestId);

	List<GenSaAcctBalanceReqEntity> findByUploadId(Long p_RequestId);
}
