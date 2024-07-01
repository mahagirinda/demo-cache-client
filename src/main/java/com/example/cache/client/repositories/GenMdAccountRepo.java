package com.example.cache.client.repositories;

import com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenMdAccountRepo extends JpaRepository<GenMdAccountEntity, Long> {
	@Query(value = "SELECT account_type FROM gen_md_account WHERE account_id=:accountId", nativeQuery = true)
	String getAccountTypeByAccountId(@Param("accountId") Long accountId);
	
	GenMdAccountEntity findByAccountCode(String accountCode);

    GenMdAccountEntity findByAccountId(Long participantAccountId);
}
