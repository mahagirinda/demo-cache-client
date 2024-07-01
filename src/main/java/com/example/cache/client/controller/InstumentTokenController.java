package com.example.cache.client.controller;

import com.example.cache.client.repositories.cache.GenTxInstrumentExceptionCacheRepository;
import com.example.cache.client.repositories.cache.InstrumentCodeTokenCacheRepository;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxInstrumentExceptionEntity;
import com.telkomsigma.framework.core.api.cache.repository.spec.PageableSearchCriteria;
import com.telkomsigma.framework.core.api.cache.repository.spec.SearchCriteria;
import com.telkomsigma.framework.core.api.cache.repository.spec.SearchOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequestMapping("/test-token")
@Slf4j
public class InstumentTokenController {

    @Autowired
    private InstrumentCodeTokenCacheRepository instrumentCodeTokenCacheRepository;

    @Autowired
    private GenTxInstrumentExceptionCacheRepository genTxInstrumentExceptionCacheRepository;

    @PostMapping("/count")
    public @ResponseBody Integer countPrice() {
        log.info("MAP PARAMETER (/count)");

        instrumentCodeTokenCacheRepository.add("INSTR_CODE_TOKEN_CALC_PRICE", "gas", "gas");
        log.info(instrumentCodeTokenCacheRepository.selectAll().toString());
        log.info(instrumentCodeTokenCacheRepository.count().toString());
        Integer count = instrumentCodeTokenCacheRepository.count().getCountString();
        log.info("RESULT COUNT : {}", count);
        return count;
    }

    @PostMapping("/test-exeption/{instrumentId}/{dateString}")
    public @ResponseBody GenTxInstrumentExceptionEntity testException(
            @PathVariable String instrumentId, @PathVariable String dateString) {
        char exceptionType = '1';
		char recordStatus = '1';

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date formattedDate = new Date();

        try {
            formattedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("Error parsing date: {}", e.getMessage());
        }

		List<SearchCriteria> searchCriteriaList = new ArrayList<>();

        log.info("instrumentId: {}", instrumentId);
        log.info("param date: {}", formattedDate);

		searchCriteriaList.add(SearchCriteria.create("instrumentId", instrumentId));
		searchCriteriaList.add(SearchCriteria.create("startDate", SearchOperation.GREATER_THAN_EQUAL, formattedDate.getTime()));
		searchCriteriaList.add(SearchCriteria.create("endDate", SearchOperation.LESS_THAN_EQUAL, formattedDate.getTime()));
		searchCriteriaList.add(SearchCriteria.create("exceptionType", exceptionType));
		searchCriteriaList.add(SearchCriteria.create("recordStatus", recordStatus));
		PageableSearchCriteria search = PageableSearchCriteria.from().addCriteria(searchCriteriaList);

		List<GenTxInstrumentExceptionEntity> genTxInstrumentExceptionEntities =
				genTxInstrumentExceptionCacheRepository.selectByCriteria(search);
		if (genTxInstrumentExceptionEntities != null && !genTxInstrumentExceptionEntities.isEmpty()) {
			return genTxInstrumentExceptionEntities.get(0);
		}
		return null;
    }
}
