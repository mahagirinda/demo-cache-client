package com.example.cache.client.controller;

import com.example.cache.client.repositories.GenTxTaskRepo;
import com.telkomsigma.eclears.app.common.entity.gen.GenTxTaskEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class GenTxTaskController {

    @Autowired
    GenTxTaskRepo genTxTaskRepo;

    @PostMapping("/task/get")
    public @ResponseBody GenTxTaskEntity get(@RequestBody HashMap<String, String> mParam) {
        log.info("getInstructionRepo param : {}", mParam);
        long taskId = Long.parseLong(mParam.get("taskId"));

        return genTxTaskRepo.findByIdTaskId(taskId);
    }
}
