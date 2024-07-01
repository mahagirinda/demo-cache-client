package com.example.cache.client.services.impl;

import com.example.cache.client.jdg.JdgFilterHelper;
import com.example.cache.client.services.JdgRestService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JdgRestServiceImpl implements JdgRestService {

    private final Logger log = LoggerFactory.getLogger(JdgRestServiceImpl.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${jdgservice.host.address}")
    private String JDG_SERVICE_HOST_ADDRESS;

    @Override
    public void addObject(String cacheName, String key, Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonRequestBody;
        try {
            jsonRequestBody = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequestBody, headers);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/add?key=%s", JDG_SERVICE_HOST_ADDRESS, JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/", cacheName, key), HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success Add {} by key {}", cacheName, key);
            log.debug("{} Add {}", cacheName, response.getBody());
        } else {
            log.error("{} Add Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public void addAllObject(String cacheName, Map<String, ?> objects) {
        log.info("Begin add all cache : {}", cacheName);
        for (String key : objects.keySet()) {
            this.addObject(cacheName, key, objects.get(key));
        }
        log.info("End add all cache : {}", cacheName);
    }

    @Override
    public void saveObject(String cacheName, String key, Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonRequestBody;
        try {
            jsonRequestBody = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequestBody, headers);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/save?key=%s", JDG_SERVICE_HOST_ADDRESS, JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/", cacheName, key), HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success Save {} by key {}", cacheName, key);
            log.debug("{} Save {}", cacheName, response.getBody());
        } else {
            log.error("{} Save Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public void deleteObject(String cacheName, String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/remove/%s", JDG_SERVICE_HOST_ADDRESS, (JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/"), cacheName, key), HttpMethod.POST, new HttpEntity<>(null, headers), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success delete {} by key {}", cacheName, key);
            log.debug("{} Delete {}", cacheName, response.getBody());
        } else {
            log.error("{} Delete Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public <E> E getObject(String cacheName, String key, Class<E> classType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/get/%s", JDG_SERVICE_HOST_ADDRESS, (JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/"), cacheName, key), HttpMethod.GET, new HttpEntity<>(null, headers), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success Get {} by key {}", cacheName, key);
            log.debug("{} Get {}", cacheName, response.getBody());
            try {
                if (isNotNullOrEmpty(response.getBody()))
                    return objectMapper.readValue(response.getBody(), classType);
                else return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            log.error("{} Get Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public List<String> getKeySet(String cacheName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/key-set", JDG_SERVICE_HOST_ADDRESS, (JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/"), cacheName), HttpMethod.GET, new HttpEntity<>(null, headers), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success Get KeySets of {}", cacheName);
            log.debug("{} KeySet {}", cacheName, response.getBody());
            try {
                if (isNotNullOrEmpty(response.getBody()))
                    return objectMapper.readValue(response.getBody(), new TypeReference<List<String>>() {
                    });
                else return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            log.error("{} KeySet Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public <E> List<E> getBySearchCriteria(String cacheName, JdgFilterHelper filter) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonRequestBody;
        try {
            jsonRequestBody = objectMapper.writeValueAsString(filter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("{} JdgFilter : {}", cacheName, jsonRequestBody);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/query", JDG_SERVICE_HOST_ADDRESS, (JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/"), cacheName), HttpMethod.POST, new HttpEntity<>(jsonRequestBody, headers), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success Search By Criteria {}", cacheName);
            log.debug("{} Search By Criteria {}", cacheName, response.getBody());
            try {
                if (isNotNullOrEmpty(response.getBody()))
                    return objectMapper.readValue(response.getBody(), new TypeReference<List<E>>() {
                    });
                else return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            log.error("{} Search By Criteria Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public void clear(String cacheName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/clear-cache", JDG_SERVICE_HOST_ADDRESS, (JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/"), cacheName), HttpMethod.POST, new HttpEntity<>(null, headers), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success clear cache {}", cacheName);
            log.debug("{} Clear {}", cacheName, response.getBody());
        } else {
            log.error("{} Clear Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public void removeCache(String cacheName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s%s/remove-cache", JDG_SERVICE_HOST_ADDRESS, (JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/"), cacheName), HttpMethod.POST, new HttpEntity<>(null, headers), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Success remove cache {}", cacheName);
            log.debug("{} Remove Cache {}", cacheName, response.getBody());
        } else {
            log.error("{} Remove Cache Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public Integer countCache(String cacheName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = new RestTemplate().exchange(String.format("%s%s/cache-service/count-data/%s", JDG_SERVICE_HOST_ADDRESS, (JDG_SERVICE_HOST_ADDRESS.endsWith("/") ? "" : "/"), cacheName), HttpMethod.POST, new HttpEntity<>(null, headers), String.class);

        if (response.getBody() != null && response.getStatusCode() == HttpStatus.OK) {
            log.info("Success Count Cache {}", cacheName);
            log.debug("{} Count Cache {}", cacheName, response.getBody());

            Pattern pattern = Pattern.compile("JDG\\s"+cacheName.toUpperCase()+"\\ssize\\s:\\s(\\d+)");
            Matcher matcher = pattern.matcher(response.getBody());

            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            } else {
                log.warn("failed to extract response body, returning 0");
                return 0;
            }
        } else {
            log.error("{} Remove Cache Status: {}", cacheName, response.getStatusCode().getReasonPhrase());
            throw new RuntimeException(response.getBody());
        }
    }


    private boolean isNotNullOrEmpty(String str) {
        return str != null && !str.trim().equals("null") && !str.trim().isEmpty();
    }

}

