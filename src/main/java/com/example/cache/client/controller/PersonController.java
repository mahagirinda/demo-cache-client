package com.example.cache.client.controller;

import com.example.cache.client.dto.Person;
import com.example.cache.client.repositories.cache.AccountCacheRepository;
import com.example.cache.client.repositories.cache.GenMdInstrumentCacheRepository;
import com.example.cache.client.repositories.cache.PersonCacheRepository;
import com.example.cache.client.services.SampleCallableTask;
import com.example.cache.client.view.PersonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telkomsigma.eclears.app.common.entity.gen.*;
import com.telkomsigma.framework.core.api.cache.repository.spec.PageableSearchCriteria;
import com.telkomsigma.framework.core.api.cache.repository.spec.SearchCriteria;
import com.telkomsigma.framework.core.api.interfaces.IDistributedExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/test-person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonCacheRepository personCacheRepository;

    @Autowired
    private AccountCacheRepository accountCacheRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IDistributedExecutor distributedExecutor;

    @PostMapping("/add")
    public @ResponseBody String add(@RequestBody Person person) {
        personCacheRepository.add(person);
        return "success";
    }

    @PostMapping("/execute-task")
    public @ResponseBody String executeDist() {
        distributedExecutor.runTaskOnRandomNodeAllCluster(SampleCallableTask.class, "personCache");
        return "success";
    }

    @PostMapping("/search")
    public @ResponseBody List<Person> search(@RequestBody PageableSearchCriteria psc) {
        String strKey = personCacheRepository.getByKey("test-case", "123456");
        log.info("Data: {}", strKey);
        return personCacheRepository.selectByCriteria(psc);
    }

    @PostMapping("/all/{nik}")
    public @ResponseBody List<Person> all(@PathVariable String nik) {
        return personCacheRepository.selectAll(nik);
    }

    @GetMapping("/get/{nik}")
    public @ResponseBody List<Object> getByNic(@PathVariable String nik) {
        return personCacheRepository.findById(nik);
    }

    @GetMapping("/inq-views")
    public @ResponseBody List<PersonView> inqueryPerson() {
        List<PersonView> viewList = personCacheRepository.findPersonView();
        log.info("List: {}", viewList);
        return viewList;
    }

    @Autowired
	private GenMdInstrumentCacheRepository genMdInstrumentCacheRepository;

    @PostMapping("/test-dummy/{instrumentCode}")
    public @ResponseBody GenMdInstrumentEntity testDummy(@PathVariable String instrumentCode) {
        return querySelect(GenMdInstrumentEntity.class, "instrumentCode", instrumentCode);
    }

    private <T> T querySelect(Class<T> className, String columnName, Object value) {
		SearchCriteria searchCriteria = SearchCriteria.create(columnName, value);
		PageableSearchCriteria search = PageableSearchCriteria.from().addCriteria(searchCriteria);

		if (className.equals(GenMdInstrumentEntity.class)) {
            log.info("masuka class: {}", className);
			return (T) genMdInstrumentCacheRepository.selectByCriteria(search).get(0);
		}

		return null;
	}
}
