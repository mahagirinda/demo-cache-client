package com.example.cache.client.services;

import com.example.cache.client.dto.Person;
import com.telkomsigma.framework.core.api.jdg.AbstractCallableTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SampleCallableTask extends AbstractCallableTask<String> {

    private Person person;

    @Override
    public String doExecute() throws RuntimeException {
        log.info("Proses data: {}", person);
        return "success";
    }

    @Override
    public void data(Object key, Object data, String trxId) {
        super.data(key, data, trxId);
        this.person = (Person) data;
    }
}
