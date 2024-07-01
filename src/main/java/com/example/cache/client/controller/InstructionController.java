package com.example.cache.client.controller;

import com.example.cache.client.repositories.cache.InstructionCacheRepository;
import com.example.cache.client.services.JPARepoService;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxInstructionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.example.cache.client.AppConstant.JDG.CACHE.GEN_TX_INSTRUCTION;

@Slf4j
@Component
@RequestMapping("/test-instruction")
public class InstructionController {

    @Autowired
    private InstructionCacheRepository instructionCacheRepository;

    @Autowired
    private JPARepoService jpaRepoService;

    @PostMapping("/get-all/{isNativeOperation}/{cachePostFix}")
    public @ResponseBody List<GenTxInstructionEntity> getAll(@PathVariable String isNativeOperation,
                                                             @PathVariable String cachePostFix) {
        log.info("MAP PARAMETER (get-all/{cachePostFix}) : cachePostFix={}", cachePostFix);
        return Boolean.parseBoolean(isNativeOperation)
                ? instructionCacheRepository.selectAll()
                : instructionCacheRepository.selectAll(cachePostFix);
    }

    @PostMapping("/get-by-key/{isNativeOperation}/{cachePostFix}")
    public @ResponseBody GenTxInstructionEntity getByKey(@PathVariable String isNativeOperation,
                                              @PathVariable String cachePostFix,
                                              @RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/get-by-key/{cachePostFix}) : cachePostFix={}, param={}", cachePostFix, param);
        Long instructionId = Long.valueOf(param.get("instructionId"));

        return Boolean.parseBoolean(isNativeOperation)
                ? instructionCacheRepository.getByKey(GEN_TX_INSTRUCTION.concat(cachePostFix), instructionId)
                : instructionCacheRepository.getById(instructionId, cachePostFix);
    }

    @PostMapping("/add/{isNativeOperation}/{cachePostFix}")
    public @ResponseBody String add(@PathVariable String isNativeOperation,
                                    @PathVariable String cachePostFix,
                                    @RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/add/{cachePostFix}) : cachePostFix={}, param={}", cachePostFix, param);
        try {
            Long instructionId = Long.valueOf(param.get("instructionId"));
                GenTxInstructionEntity genTxInstructionEntity = jpaRepoService.getTxInstructionByInstructionId(instructionId);
                if (genTxInstructionEntity == null)
                    return "failed genTxInstructionEntity is null";

            if (Boolean.parseBoolean(isNativeOperation))
                instructionCacheRepository.add(GEN_TX_INSTRUCTION.concat(cachePostFix), instructionId, genTxInstructionEntity);
            else
                instructionCacheRepository.save(genTxInstructionEntity, cachePostFix);

            return "success";
        } catch (Exception e) {
            e.getCause();
            return "failed : \n" + e.getCause();
        }
    }

    @PostMapping("/clear/{cachePostFix}")
    public @ResponseBody String clear(@PathVariable String cachePostFix) {
        log.info("MAP PARAMETER (/clear/{cachePostFix}) : cachePostFix={}", cachePostFix);
        try {
            instructionCacheRepository.clear(GEN_TX_INSTRUCTION.concat(".").concat(cachePostFix));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
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
            Long instructionId = Long.valueOf(param.get("instructionId"));
            if (Boolean.parseBoolean(isNativeOperation))
                instructionCacheRepository.remove(GEN_TX_INSTRUCTION.concat("."+cachePostFix), instructionId);
            else
                instructionCacheRepository.remove(instructionId, cachePostFix);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return "failed : \n" + e.getCause();
        }
    }

    @PostMapping("/remove-cache/{cachePostFix}")
    public @ResponseBody String removeCache(@PathVariable String cachePostFix) {
        log.info("MAP PARAMETER (/remove-cache/{cachePostFix}) : cachePostFix={}", cachePostFix);
        try {
            instructionCacheRepository.removeCache(GEN_TX_INSTRUCTION.concat(".").concat(cachePostFix));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return "failed : \n" + e.getCause();
        }
    }
}
