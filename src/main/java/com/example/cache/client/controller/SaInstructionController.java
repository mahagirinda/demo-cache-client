package com.example.cache.client.controller;

import com.example.cache.client.repositories.GenSaInstructionRepository;
import com.example.cache.client.repositories.cache.GenSaInstructionCacheRepository;
import com.example.cache.client.repositories.cache.MemberCacheRepository;
import com.example.cache.client.view.MemberView;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdMemberEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenSaInstructionEntity;
import com.telkomsigma.framework.core.api.cache.repository.spec.PageableSearchCriteria;
import com.telkomsigma.framework.core.api.cache.repository.spec.SearchCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequestMapping("/test-sa-instruction")
@Slf4j
public class SaInstructionController {

    @Autowired
    GenSaInstructionCacheRepository getGenSaInstructionCacheRepository;

    @Autowired
    GenSaInstructionRepository genSaInstructionRepository;

    @GetMapping("/get-all")
    public @ResponseBody List<GenSaInstructionEntity> getAll() {
        return getGenSaInstructionCacheRepository.selectAll();
    }

    @PostMapping("/populate/{uploadId}")
    public @ResponseBody boolean populateCacheData(@PathVariable String uploadId) {
        Iterable<GenSaInstructionEntity> list = genSaInstructionRepository.findAll();

        Map<Object, Object> map = StreamSupport.stream(list.spliterator(), false)
        .collect(Collectors.toMap(
                GenSaInstructionEntity::getInstructionId,
                entity -> entity
        ));

        getGenSaInstructionCacheRepository.addAll("GEN_SA_INSTRUCTION." + uploadId, map);

        return true;
    }

    @PostMapping("/get-all-postfix/{uploadId}")
    public @ResponseBody List<GenSaInstructionEntity> getAllPostfix(@PathVariable String uploadId) {
        return getGenSaInstructionCacheRepository.selectAll(uploadId);
    }

    @PostMapping("/count-data/{uploadId}")
    public @ResponseBody Integer count(@PathVariable String uploadId) {
        long start1 = System.currentTimeMillis();
        int cacheSize1 = getGenSaInstructionCacheRepository.selectAll(uploadId).size();
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        int cacheSize2 = getGenSaInstructionCacheRepository.keySet("GEN_SA_INSTRUCTION." + uploadId).size();
        long end2 = System.currentTimeMillis();

        long start3 = System.currentTimeMillis();
        int cacheSize3 = getGenSaInstructionCacheRepository.count(uploadId).getCountInstructionId();
        long end3 = System.currentTimeMillis();

        log.info("Count select all size result : {}, time : {} ms", cacheSize1, end1 - start1);
        log.info("Count key set size result : {}, time : {} ms", cacheSize2, end2 - start2);
        log.info("Count query view count result : {}, time : {} ms", cacheSize3, end3 - start3);

        return cacheSize2;

    }
}
