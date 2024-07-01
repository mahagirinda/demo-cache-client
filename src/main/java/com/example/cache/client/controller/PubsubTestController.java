package com.example.cache.client.controller;

import com.example.cache.client.repositories.cache.PubsubTradeManagementCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class PubsubTestController {

    @Autowired
    PubsubTradeManagementCacheRepository pubsubTradeManagementCacheRepository;

    @PostMapping("/pubsub/test")
    public @ResponseBody List<HashMap<Object, Object>> pubsubTest() {
        HashMap<Object, Object> data1 = new HashMap<>();
        data1.put("1", "satu");
        data1.put("2", "dua");

        HashMap<Object, Object> data2 = new HashMap<>();
        data2.put("3", "tiga");
        data2.put("4", "empat");

        pubsubTradeManagementCacheRepository.add("PUBSUB_TRADEMGMT", "1", data1);
        pubsubTradeManagementCacheRepository.add("PUBSUB_TRADEMGMT", "2", data2);

        HashMap<Object, Object> map1 = pubsubTradeManagementCacheRepository.getByKey("PUBSUB_TRADEMGMT", "1");
        log.info("data 1 : {}", map1);

        HashMap<Object, Object> map2 = pubsubTradeManagementCacheRepository.getByKey("PUBSUB_TRADEMGMT", "2");
        log.info("data 2 : {}", map2);

        List<HashMap<Object, Object>> response = pubsubTradeManagementCacheRepository.selectAll();

        return (response);
    }

    public static List<HashMap<Object, Object>> convert(List<Object> list) {
        List<HashMap<Object, Object>> resultList = new ArrayList<>();

        for (Object obj : list) {
            if (obj instanceof HashMap) { // Check if the object is of type HashMap<Object, Object>
                @SuppressWarnings("unchecked")
                HashMap<Object, Object> map = (HashMap<Object, Object>) obj;
                resultList.add(map);
            } else {
                System.err.println("Object is not of type HashMap<Object, Object>: " + obj);
            }
        }

        return resultList;
    }
}
