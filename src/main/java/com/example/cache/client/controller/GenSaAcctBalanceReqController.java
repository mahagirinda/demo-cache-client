package com.example.cache.client.controller;

import com.example.cache.client.repositories.GenSaAcctBalanceReqRepo;
import com.example.cache.client.repositories.cache.GenSaAcctBalanceReqCacheRepository;
import com.telkomsigma.eclears.app.common.entity.gen.GenSaAcctBalanceReqEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/test-entryupload")
@Slf4j
public class GenSaAcctBalanceReqController {
    @Autowired
    GenSaAcctBalanceReqCacheRepository genSaAcctBalanceReqCacheRepository;

    @Autowired
    GenSaAcctBalanceReqRepo genSaAcctBalanceReqRepo;

    @GetMapping("/count-cache")
    public @ResponseBody Integer countCache() {
        GenSaAcctBalanceReqEntity genSaAcctBalanceReqEntity = genSaAcctBalanceReqRepo.findByAccountId(831L);
        log.info("genSaAcctBalanceReqEntity result {}", genSaAcctBalanceReqEntity.getAccountId());
        genSaAcctBalanceReqCacheRepository.add("GEN_SA_ACCT_BALANCE_REQ", genSaAcctBalanceReqEntity.getRequestId(), genSaAcctBalanceReqEntity);
        log.info("genSaAcctBalanceReqCacheRepository result {}", genSaAcctBalanceReqCacheRepository.getByAccountId(genSaAcctBalanceReqEntity.getAccountId()).getAccountId());
        return genSaAcctBalanceReqCacheRepository.count().getCountAccountId();
    }
}
