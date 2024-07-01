package com.example.cache.client;

import com.example.cache.client.config.EmbeddedRhdgCacheConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties(EmbeddedRhdgCacheConfig.class)
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = "com")
public class DemoCacheClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCacheClientApplication.class, args);
	}

}
