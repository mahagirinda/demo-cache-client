package com.example.cache.client.controller;

import com.example.cache.client.repositories.GenMdAccountRepo;
import com.example.cache.client.repositories.cache.AccountCacheRepository;
import com.example.cache.client.services.EmailService;
import com.example.cache.client.view.AccountView;
import com.telkomsigma.eclears.app.common.entity.gen.GenMdAccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;

@Component
@RequestMapping("/test-account")
@Slf4j
public class EmailController {

    @PostMapping("/send-test-mail")
    public @ResponseBody String sendMail(@RequestBody HashMap<String, String> param) throws MessagingException {
        log.info("MAP PARAMETER (/send-test-mail) : {}", param);
        String to = param.get("to");
        String subject = param.get("subject");
        String body = param.get("body");
        return EmailService.sendEmail(to, subject, body);
    }
}
