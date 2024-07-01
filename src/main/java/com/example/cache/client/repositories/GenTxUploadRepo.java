package com.example.cache.client.repositories;

import com.telkomsigma.eclears.app.common.entity.gen.GenTxUploadEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenTxUploadRepo extends PagingAndSortingRepository<GenTxUploadEntity, Long> {

    GenTxUploadEntity findByUploadId(Long id);

}
