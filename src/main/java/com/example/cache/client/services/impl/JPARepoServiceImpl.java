package com.example.cache.client.services.impl;

import com.example.cache.client.repositories.GenTxInstructionRepo;
import com.example.cache.client.repositories.GenTxUploadRepo;
import com.example.cache.client.services.JPARepoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxInstructionEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxUploadEntity;
import com.telkomsigma.framework.core.api.uploadfile.entity.GenTxUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
public class JPARepoServiceImpl implements JPARepoService {
    @Autowired
    GenTxInstructionRepo genTxInstructionRepo;

    @Autowired
    GenTxUploadRepo genTxUploadRepo;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GenTxInstructionEntity getTxInstructionByInstructionDummy(Long id) {
        GenTxInstructionEntity genTxInstructionEntity = new GenTxInstructionEntity();
        genTxInstructionEntity.setInstructionId(id);
        genTxInstructionEntity.setSettlementDate(new Date());
        genTxInstructionEntity.setTradeDate(new Date());
        genTxInstructionEntity.setExternalReference("Maha 123");
        genTxInstructionEntity.setInstructionTypeId(4000);
        genTxInstructionEntity.setInstructionPriority(4000);
        genTxInstructionEntity.setCreatedBy("MAHA 123");
        genTxInstructionEntity.setCreatedOn(new Date());

        return genTxInstructionEntity;
    }

    @Override
    public GenTxInstructionEntity getTxInstructionByInstructionId(Long id) {
        GenTxInstructionEntity genTxInstruction = genTxInstructionRepo.findByInstructionId(id);

        log.info("Get GenTxInstructionEntity {} : {}", id, genTxInstruction);
        return genTxInstruction;
    }

    @Override
    public GenTxUpload getTxUploadByUploadDummy(Long uploadId) {
        GenTxUpload genTxUpload = new GenTxUpload();
        genTxUpload.setUploadId(uploadId);
        genTxUpload.setUploadFileName("Maha Girinda");
        genTxUpload.setTotalData(12);
        genTxUpload.setUploadDate(new Date());

        return genTxUpload;
    }

    @Override
    public GenTxUpload getTxUploadByUploadId(Long uploadId) {
        GenTxUploadEntity genTxUploadEntity = genTxUploadRepo.findByUploadId(uploadId);

        log.info("Get genTxUploadEntity {} : {}", uploadId, genTxUploadEntity);
        if (Objects.isNull(genTxUploadEntity))
            return new GenTxUpload();

        GenTxUpload genTxUpload = new GenTxUpload();
        genTxUpload.setUploadId(genTxUploadEntity.getUploadId());
        genTxUpload.setCreatedBy(genTxUploadEntity.getCreatedBy());
        genTxUpload.setCreatedOn(genTxUploadEntity.getCreatedOn());
        genTxUpload.setFailData(Math.toIntExact(genTxUploadEntity.getFailData()));
        genTxUpload.setMediaTypeCode(String.valueOf(genTxUploadEntity.getMediaTypeCode()));
        genTxUpload.setModifiedBy(genTxUploadEntity.getModifiedBy());
        genTxUpload.setModifiedOn(genTxUploadEntity.getModifiedOn());
        genTxUpload.setPassData(Math.toIntExact(genTxUploadEntity.getPassData()));
        genTxUpload.setPassDataCorrection(Math.toIntExact(genTxUploadEntity.getPassDataCorrection()));
        genTxUpload.setPassDataNote(Math.toIntExact(genTxUploadEntity.getPassDataNote()));
        genTxUpload.setProcessEndOn(genTxUploadEntity.getProcessEndOn());
        genTxUpload.setProcessStartOn(genTxUploadEntity.getProcessStartOn());
        genTxUpload.setProcessStatusCode(genTxUploadEntity.getProcessStatusCode());
        genTxUpload.setTotalData(Math.toIntExact(genTxUploadEntity.getTotalData()));
        genTxUpload.setUploadDate(genTxUploadEntity.getUploadDate());
        genTxUpload.setUploadEndOn(genTxUploadEntity.getUploadEndOn());
        genTxUpload.setUploadFileHash(genTxUploadEntity.getUploadFileHash());
        genTxUpload.setUploadFileLocation(genTxUploadEntity.getUploadFileLocation());
        genTxUpload.setUploadFileName(genTxUploadEntity.getUploadFileName());
        genTxUpload.setUploadFormatId(genTxUploadEntity.getUploadFormatId());
        genTxUpload.setUploadStartOn(genTxUploadEntity.getUploadStartOn());

        log.info("Result genTxUpload {} : {}", uploadId, genTxUpload);
        return genTxUpload;
    }

}
