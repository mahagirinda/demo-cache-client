package com.example.cache.client.config;

import com.telkomsigma.framework.core.api.config.AbstractDistributedExecConfigurer;
import com.telkomsigma.framework.core.api.interfaces.IDistributedExecutor;
import com.telkomsigma.framework.core.api.service.DistributedExecutor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@EnableCaching
public class DistributedExecutionConfigurer extends AbstractDistributedExecConfigurer {

    @Bean
    public IDistributedExecutor distributedExecutor() {
        DistributedExecutor distributedExecutor = new DistributedExecutor();
        distributedExecutor.setName(UUID.randomUUID().toString());
        distributedExecutor.setGroup("dist-executor");
        return distributedExecutor;
    }


}
