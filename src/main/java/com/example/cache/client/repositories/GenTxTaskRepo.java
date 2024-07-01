package com.example.cache.client.repositories;

import com.telkomsigma.eclears.app.common.entity.gen.GenTxTaskEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxTaskEntityPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("GenTxTaskRepo")
public interface GenTxTaskRepo extends PagingAndSortingRepository<GenTxTaskEntity, GenTxTaskEntityPK> {

	@Query("FROM GenTxTaskEntity e WHERE e.id.taskId = :taskId")
	GenTxTaskEntity findByIdTaskId(@Param("taskId") long taskId);
}
