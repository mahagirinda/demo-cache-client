package com.example.cache.client.controller;

import com.example.cache.client.repositories.cache.MemberCacheRepository;
import com.example.cache.client.view.MemberView;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdMemberEntity;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
@RequestMapping("/test-member")
@Slf4j
public class MemberController {
    @Autowired
    private MemberCacheRepository memberCacheRepository;

    @PostMapping("/get-all")
    public @ResponseBody List<GenMdAccountEntity> getAll(@RequestBody HashMap<String, String> param) {
        log.info("MAP PARAMETER (/get-all) : {}", param);
        int page   = Integer.parseInt(param.get("page"));
        int limit   = Integer.parseInt(param.get("limit"));
        String direction = String.valueOf(param.get("sort"));
        String orderBy   = String.valueOf(param.get("orderBy"));
        Sort sort = Sort.by(direction.equalsIgnoreCase("acs") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);

        Pageable pageable = PageRequest.of(page, limit, sort);
        return memberCacheRepository.selectAll(pageable);
    }

    @PostMapping("/get-by-member-id/{memberId}")
    public @ResponseBody GenMdMemberEntity getByMemberId(@PathVariable String memberId) {
        log.info("MAP PARAMETER (/get-by-member-id/{memberId}) : {}", memberId);
        return memberCacheRepository.getByMemberId(Long.valueOf(memberId));
    }

    @PostMapping("/get-by-member-code/{memberCode}")
    public @ResponseBody GenMdMemberEntity getByMemberCode(@PathVariable String memberCode) {
        log.info("MAP PARAMETER (/get-by-member-code/{memberCode}) : {}", memberCode);
        GenMdMemberEntity genMdMemberEntity = memberCacheRepository.getByMemberCode(memberCode);
        log.info("DATA : {}", genMdMemberEntity);
        return genMdMemberEntity;
    }

    @PostMapping("/get-by-member-code-simple/{memberCode}")
    public @ResponseBody MemberView getByMemberCodeSimplified(@PathVariable String memberCode) {
        log.info("MAP PARAMETER (/get-by-member-code-simple/{memberCode}) : {}", memberCode);
        MemberView memberView = memberCacheRepository.getMemberSimpleByMemberCode(memberCode);
        log.info("DATA : {}", memberView);
        return memberView;
    }

    @PostMapping("/get-member-code-by-id/{memberId}")
    public @ResponseBody String getByMemberCodeById(@PathVariable Long memberId) {
        log.info("MAP PARAMETER (/get-member-code-by-id/{memberId}) : {}", memberId);
        List<Object> memberCode = memberCacheRepository.getMemberCodeByMemberId(memberId);
        log.info("DATA : {}", memberCode.get(0));
        return "test";
    }

    @PostMapping("/test-dummy/{shortCode}")
    public @ResponseBody GenMdMemberEntity testDummy(@PathVariable String shortCode) {
        String[] columnName = {"shortCode", "memberStatus"};
		Object[] value = {shortCode, "1"};

        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
		searchCriteriaList.add(SearchCriteria.create(columnName[0], value[0]));
		searchCriteriaList.add(SearchCriteria.create(columnName[1], value[1]));

		PageableSearchCriteria search = PageableSearchCriteria.from().addCriteria(searchCriteriaList);
		return memberCacheRepository.selectByCriteria(search).get(0);
    }
}
