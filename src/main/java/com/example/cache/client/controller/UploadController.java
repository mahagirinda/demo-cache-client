package com.example.cache.client.controller;

import com.example.cache.client.repositories.cache.UploadCacheRepository;
import com.example.cache.client.services.JPARepoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telkomsigma.framework.core.api.uploadfile.entity.GenTxUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.example.cache.client.AppConstant.JDG.CACHE.GEN_TX_UPLOAD;

@Slf4j
@Component
@RequestMapping("/test-upload")
public class UploadController {

    @Autowired
    private UploadCacheRepository uploadCacheRepository;

    @Autowired
    private JPARepoService jpaRepoService;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/get-all/{isNativeOperation}/{cachePostFix}")
    public @ResponseBody List<GenTxUpload> getAll(@PathVariable String isNativeOperation,
                                                  @PathVariable String cachePostFix) throws JsonProcessingException {
        log.info("MAP PARAMETER (get-all/{cachePostFix}) : cachePostFix={}", cachePostFix);
        List<GenTxUpload> genTxUpload = Boolean.parseBoolean(isNativeOperation)
                ? uploadCacheRepository.selectAll()
                : uploadCacheRepository.selectAll(cachePostFix);

        log.info("RESULT (get-all/{cachePostFix}) genTxUpload : {}", objectMapper.writeValueAsString(genTxUpload));
        return genTxUpload;
    }

    @PostMapping("/get-by-key/{isNativeOperation}/{cachePostFix}")
    public @ResponseBody GenTxUpload getByKey(@PathVariable String isNativeOperation,
                                              @PathVariable String cachePostFix,
                                              @RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/get-by-key/{cachePostFix}) : cachePostFix={}, param={}", cachePostFix, param);
        Long uploadIdLong = Long.valueOf(param.get("uploadId"));

        return Boolean.parseBoolean(isNativeOperation)
                ? uploadCacheRepository.getByKey(GEN_TX_UPLOAD.concat(cachePostFix), uploadIdLong)
                : uploadCacheRepository.getById(uploadIdLong, cachePostFix);
    }

    @PostMapping("/add/{isNativeOperation}/{cachePostFix}")
    public @ResponseBody String add(@PathVariable String isNativeOperation,
                                    @PathVariable String cachePostFix,
                                    @RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/add/{cachePostFix}) : cachePostFix={}, param={}", cachePostFix, param);
        try {
            Long uploadIdLong = Long.valueOf(param.get("uploadId"));
            GenTxUpload genTxUpload = jpaRepoService.getTxUploadByUploadDummy(uploadIdLong);

            if (Objects.isNull(genTxUpload))
                return "failed genTxUpload is null";
            else log.info("RESULT {}", genTxUpload);

            if (Boolean.parseBoolean(isNativeOperation))
                uploadCacheRepository.save(GEN_TX_UPLOAD.concat(cachePostFix), uploadIdLong, genTxUpload);
            else
                uploadCacheRepository.save(genTxUpload, cachePostFix);

            return "success";
        } catch (Exception e) {
            e.getCause();
            return "failed : \n" + e.getCause() + e.getMessage() + Arrays.toString(e.getStackTrace());
        }
    }

    @PostMapping("/add")
    public @ResponseBody String addWithoutPostfix(@RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/add)");
        try {
            Long uploadIdLong = Long.valueOf(param.get("uploadId"));
            GenTxUpload genTxUpload = jpaRepoService.getTxUploadByUploadDummy(uploadIdLong);

            uploadCacheRepository.save(genTxUpload);

            return "success";
        } catch (Exception e) {
            e.getCause();
            return "failed : \n" + e.getCause() + e.getMessage() + Arrays.toString(e.getStackTrace());
        }
    }

    @PostMapping("/clear/{cachePostFix}")
    public @ResponseBody String clear(@PathVariable String cachePostFix) {
        log.info("MAP PARAMETER (/clear/{cachePostFix}) : cachePostFix={}", cachePostFix);
        try {
            uploadCacheRepository.clear(GEN_TX_UPLOAD.concat(".").concat(cachePostFix));
            return "success";
        } catch (Exception e) {
            e.getCause();
            return "failed : \n" + e.getCause();
        }
    }

    @PostMapping("/remove-by-key/{isNativeOperation}/{cachePostFix}")
    public @ResponseBody String removeByKey(@PathVariable String isNativeOperation,
                                            @PathVariable String cachePostFix,
                                            @RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/remove-by-key/{cachePostFix}) : cachePostFix={}, param={}", cachePostFix, param);
        try {
            Long uploadId = Long.valueOf(param.get("uploadId"));
            if (Boolean.parseBoolean(isNativeOperation))
                uploadCacheRepository.remove(GEN_TX_UPLOAD.concat("."+cachePostFix), uploadId);
            else
                uploadCacheRepository.remove(uploadId, cachePostFix);

            return "success";
        } catch (Exception e) {
            e.getCause();
            return "failed : \n" + e.getCause();
        }
    }

    @PostMapping("/remove-cache/{cachePostFix}")
    public @ResponseBody String removeCache(@PathVariable String cachePostFix) {
        log.info("MAP PARAMETER (/remove-cache/{cachePostFix}) : cachePostFix={}", cachePostFix);
        try {
            uploadCacheRepository.removeCache(GEN_TX_UPLOAD.concat(".").concat(cachePostFix));
            return "success";
        } catch (Exception e) {
            e.getCause();
            return "failed : \n" + e.getCause();
        }
    }

    @PostMapping("/count")
    public @ResponseBody Integer countUpload() {
        log.info("MAP PARAMETER (/count)");
        Integer count =  uploadCacheRepository.count().getCountUploadId();
        log.info("RESULT COUNT : {}", count);
        return count;
    }
}
