package com.example.cache.client.services.impl;

import com.example.cache.client.repositories.InstructionInquiryRepo;
import com.example.cache.client.services.InquiryService;
import com.example.cache.client.specification.InquirySpecification;
import com.telkomsigma.eclears.app.common.viewentity.inquiry.ViewInquiryInstruction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    InstructionInquiryRepo instructionInquiryRepo;

    public static final String JSON_KEY_LIMIT = "limit";
    public static final String JSON_KEY_PAGE = "page";
    public static final String JSON_KEY_DIRECTION = "direction";
    public static final String JSON_KEY_ORDER_BY = "orderBy";

    public static final String paramSize = "size";
    public static final String paramList = "list";

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getListInquiryInstruction(Map<String, Object> filter) {
        log.info("getListInquiryInstruction param : {}", filter);
        HashMap<String, String> query = (HashMap<String, String>) filter.get("query");
        Page<ViewInquiryInstruction> pageResult = instructionInquiryRepo.findAll(InquirySpecification.inquiryInstructionSpec(query),
                getPageable(filter));
        List<ViewInquiryInstruction> resultQuery = pageResult.getContent();
        Map<String, Object> result = new HashMap<>();
        result.put(paramList, resultQuery);
        result.put(paramSize, pageResult.getTotalElements());
        return result;
    }

    private Pageable getPageable(Map<String, Object> filter) {
        int limit = (int) filter.get(JSON_KEY_LIMIT);
        int page = (int) filter.get(JSON_KEY_PAGE);
        String orderBy = filter.get(JSON_KEY_ORDER_BY).toString();
        String direction = filter.get(JSON_KEY_DIRECTION).toString();
        Sort sort = null;
        if (direction.equalsIgnoreCase("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, orderBy);
        } else {
            sort = Sort.by(Sort.Direction.DESC, orderBy);
        }

        return PageRequest.of(page, limit, sort);
    }
}
