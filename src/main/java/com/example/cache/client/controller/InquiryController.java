package com.example.cache.client.controller;

import com.example.cache.client.services.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/inquiry")
@Slf4j
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/getInstructionRepo")
    public @ResponseBody ResponseEntity<String> getInstructionRepo(@RequestBody HashMap<String, Object> mParam) {
        log.info("getInstructionRepo param : {}", mParam);
        Map<String, Object> result = inquiryService.getListInquiryInstruction(mParam);
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }
}
