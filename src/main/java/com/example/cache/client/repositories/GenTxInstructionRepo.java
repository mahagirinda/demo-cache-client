package com.example.cache.client.repositories;

import com.telkomsigma.eclears.app.common.entity.gen.GenTxInstructionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenTxInstructionRepo extends PagingAndSortingRepository<GenTxInstructionEntity, Long> {

    GenTxInstructionEntity findByInstructionId(Long instructionId);

}
