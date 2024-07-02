package com.example.cache.client.repositories;

import com.telkomsigma.eclears.app.common.entity.gen.GenSaInstructionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenSaInstructionRepository extends PagingAndSortingRepository<GenSaInstructionEntity, String> {

}
