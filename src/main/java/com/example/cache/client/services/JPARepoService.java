package com.example.cache.client.services;

import com.telkomsigma.eclears.app.common.entity.gen.GenSaInstructionEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxInstructionEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxUploadEntity;
import com.telkomsigma.framework.core.api.uploadfile.entity.GenTxUpload;

public interface JPARepoService {

    GenTxInstructionEntity getTxInstructionByInstructionDummy(Long id);

    GenTxInstructionEntity getTxInstructionByInstructionId(Long id);

    GenTxUpload getTxUploadByUploadDummy(Long uploadId);

    GenTxUpload getTxUploadByUploadId(Long uploadId);

}
