package com.example.cache.client.view;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class MyViewer implements InvocationHandler {

    private Map<String, Object> data;

    public MyViewer(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String fieldName = method.getName();
        log.info("method name: {}", method.getName());
        if (fieldName.startsWith("get")) {
            fieldName = fieldName.substring(3);
        } else if (fieldName.startsWith("is")) {
            fieldName = fieldName.substring(2);
        }
        fieldName = String.format("%s%s", fieldName.substring(0, 1).toLowerCase(), fieldName.substring(1));
        log.info("Map: {} -> {} : {}", method, fieldName, data.get(fieldName));
        return data.get(fieldName);
    }
}
