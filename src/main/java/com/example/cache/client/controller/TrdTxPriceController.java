package com.example.cache.client.controller;

import com.example.cache.client.repositories.cache.TrdTxPriceCacheRepository;
import com.telkomsigma.eclears.app.common.entity.trd.TrdTxPriceEntity;
import com.telkomsigma.framework.core.api.cache.repository.spec.PageableSearchCriteria;
import com.telkomsigma.framework.core.api.cache.repository.spec.SearchCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@RequestMapping("/test-price")
@Slf4j
public class TrdTxPriceController {

    @Autowired
    private TrdTxPriceCacheRepository trdTxPriceCacheRepository;

    @PostMapping("/get-by-id")
    public @ResponseBody List<TrdTxPriceEntity> get(@RequestBody HashMap<String, String> filter) {
        String instrumentCode = filter.get("instrumentCode");
        String marketCode = filter.get("marketCode");
        Long priceDate = Long.valueOf(filter.get("priceDate"));

        log.info("\n");
        log.info("======================================");
        log.info("Parameter : {}, {}, {}", instrumentCode, marketCode, priceDate);

        List<TrdTxPriceEntity> resultList = trdTxPriceCacheRepository.selectByInstrumentCodeAndMarketCodeAndPriceDateList(
                instrumentCode, marketCode, priceDate
        );
        log.info("resultList : {}", resultList);

        return resultList;
    }

    @PostMapping("/count")
    public @ResponseBody Integer countPrice() {
        log.info("MAP PARAMETER (/count)");
        Integer count = trdTxPriceCacheRepository.count().getCountUploadId();
        log.info("RESULT COUNT : {}", count);
        return count;
    }
}
