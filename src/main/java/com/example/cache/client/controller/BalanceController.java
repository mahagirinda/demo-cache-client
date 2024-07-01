package com.example.cache.client.controller;

import com.example.cache.client.AppConstant;
import com.example.cache.client.repositories.cache.BalanceCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Objects;

@Component
@RequestMapping("/test-balance")
@Slf4j
public class BalanceController {

    @Autowired
    private BalanceCacheRepository balanceCacheRepository;

    @PostMapping("/get-all")
    public @ResponseBody String getAll() {
        return balanceCacheRepository.selectAll().toString();
    }

    @PostMapping("/get-by-key")
    public @ResponseBody String getByKey(@RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/get-by-key) : {}", param);
        Long accountDebit   = Long.valueOf(param.get("accountDebit"));
        Long instrumentId   = Long.valueOf(param.get("instrumentId"));
        String balanceType  = String.valueOf(param.get("balanceType"));
        Integer loadPeriod  = Integer.valueOf(param.get("loadPeriod"));

        Object objectBalance = balanceCacheRepository.getByKey(AppConstant.JDG.CACHE.BALANCE, getKeyBalance(
                accountDebit, instrumentId, balanceType, loadPeriod
        ));
        return Objects.toString(objectBalance, null);
    }

    @PostMapping("/add")
    public @ResponseBody String add(@RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/add) : {}", param);
        Long accountDebit   = Long.valueOf(param.get("accountDebit"));
        Long instrumentId   = Long.valueOf(param.get("instrumentId"));
        String balanceType  = String.valueOf(Long.valueOf(param.get("balanceType")));
        Integer loadPeriod  = Integer.valueOf(param.get("loadPeriod"));
        String balance      = String.valueOf(param.get("balance"));

        String key = getKeyBalance(accountDebit, instrumentId, balanceType, loadPeriod);
        try {
            balanceCacheRepository.add(AppConstant.JDG.CACHE.BALANCE, key, balance);
            return "success";
        } catch (Exception e) {
            e.getCause();
            return "failed";
        }
    }

    private String getKeyBalance(Long p_AccountDebit, Long p_InstrumentId, String p_BalanceType, Integer p_LoanPeriod) {
        return String.valueOf(p_AccountDebit)
                .concat(AppConstant.CHARACTER.UNDERSCORE)
                .concat(String.valueOf(p_InstrumentId))
                .concat(AppConstant.CHARACTER.UNDERSCORE)
                .concat(p_BalanceType)
                .concat(AppConstant.CHARACTER.UNDERSCORE)
                .concat(String.valueOf(p_LoanPeriod));
    }
}
