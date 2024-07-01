package com.example.cache.client.controller;

import com.example.cache.client.repositories.GenMdAccountRepo;
import com.example.cache.client.repositories.cache.AccountCacheRepository;
import com.example.cache.client.view.AccountView;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Component
@RequestMapping("/test-account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountCacheRepository accountCacheRepository;

    @Autowired
    private GenMdAccountRepo genMdAccountRepo;

    @PostMapping("/get-all")
    public @ResponseBody List<GenMdAccountEntity>  getAll(@RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/get-all) : {}", param);
        int page   = Integer.parseInt(param.get("page"));
        int limit   = Integer.parseInt(param.get("limit"));
        String direction = String.valueOf(param.get("sort"));
        String orderBy   = String.valueOf(param.get("orderBy"));
        Sort sort = Sort.by(direction.equalsIgnoreCase("acs") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);

        Pageable pageable = PageRequest.of(page, limit, sort);
        return accountCacheRepository.selectAll(pageable);
        //return Objects.toString(accountCacheRepository.selectAll(), null);
    }

    @PostMapping("/get-by-criteria")
    public @ResponseBody List<AccountView> getByAccountByMemberIdAndClientCodeAndAccountType(@RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/get-by-criteria) : {}", param);
        Long memberId       = Long.valueOf(param.get("memberId"));
        String clientCode   = String.valueOf(param.get("clientCode"));
        String accountType  = String.valueOf(param.get("accountType"));

        return accountCacheRepository.getByAccountByMemberIdAndClientCodeAndAccountTypeSimple(memberId, clientCode, accountType);
    }

    @PostMapping("/get-by-account-id/{accountId}")
    public @ResponseBody GenMdAccountEntity getByAccountId(@PathVariable String accountId) {
        log.info("MAP PARAMETER (/get-by-account-id/{accountId}) : {}", accountId);
        return accountCacheRepository.getByAccountId(Long.valueOf(accountId));
    }

    @PostMapping("/get-by-account-code/{accountCode}")
    public @ResponseBody GenMdAccountEntity getByAccountCode(@PathVariable String accountCode) {
        log.info("MAP PARAMETER (/get-by-account-code/{accountCode}) : {}", accountCode);
        GenMdAccountEntity genMdAccountEntityList = accountCacheRepository.getByAccountCode(accountCode);
        log.info("DATA : {}", genMdAccountEntityList);
        return genMdAccountEntityList;
    }

    @PostMapping("/count")
    public @ResponseBody Integer countAccount() {
        log.info("MAP PARAMETER (/count)");
        Integer count =  accountCacheRepository.dataCount().getCountAccountId();
        log.info("RESULT COUNT : {}", count);
        return count;
    }

    @PostMapping("/db/get-by-account-code/{accountCode}")
    public @ResponseBody GenMdAccountEntity getDBAccountByAccountCode(@PathVariable String accountCode) {
        log.info("MAP PARAMETER (/db/get-by-account-code) with param : {}", accountCode);
        return genMdAccountRepo.findByAccountCode(accountCode);
    }
}
