package com.example.cache.client.controller.rest;

import com.example.cache.client.jdg.JdgFilterHelper;
import com.example.cache.client.services.JdgRestService;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdInstrumentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.cache.client.AppConstant.JDG.CACHE.GEN_MD_INSTRUMENT;

@Slf4j
@Component
@RequestMapping("/rest/instrument")
public class InstrumentController {

    @Autowired
    JdgRestService jdgRestService;

    @PostMapping("/count")
    public @ResponseBody Integer count() {
        return jdgRestService.getKeySet(GEN_MD_INSTRUMENT).size();
    }

    @PostMapping("/get-by-key/{instrumentId}")
    public @ResponseBody GenMdInstrumentEntity get(@PathVariable String instrumentId) {
        return jdgRestService.getObject(GEN_MD_INSTRUMENT, instrumentId, GenMdInstrumentEntity.class);
    }

    @PostMapping("/add/{instrumentId}")
    public @ResponseBody Boolean add(@PathVariable String instrumentId, @RequestBody GenMdInstrumentEntity data) {
        log.info("instrument to save  {} : {}", instrumentId, data);
        jdgRestService.addObject(GEN_MD_INSTRUMENT, instrumentId, data);
        return true;
    }

    @PostMapping("/save/{instrumentId}")
    public @ResponseBody Boolean save(@PathVariable String instrumentId, @RequestBody GenMdInstrumentEntity data) {
        log.info("instrument to save  {} : {}", instrumentId, data);
        jdgRestService.saveObject(GEN_MD_INSTRUMENT, instrumentId, data);
        return true;
    }

    @PostMapping("/delete/{instrumentId}")
    public @ResponseBody Boolean delete(@PathVariable String instrumentId) {
        log.info("instrument to delete  {}", instrumentId);
        jdgRestService.deleteObject(GEN_MD_INSTRUMENT, instrumentId);
        return true;
    }

    @PostMapping("/get-by-criteria/")
    public @ResponseBody List<GenMdInstrumentEntity> select() {
        JdgFilterHelper filterHelper = new JdgFilterHelper();
		Map<String, String> paramValue = new HashMap<>();

        filterHelper.setFilterInRPN(new String[]{
                "instrumentCode",
                ":instrumentCode",
                "="
        });

		paramValue.put(":instrumentCode", "IDR");
		filterHelper.setParamValues(paramValue);

		return jdgRestService.getBySearchCriteria(GEN_MD_INSTRUMENT, filterHelper);
    }
}
