package com.example.cache.client.config;

import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepositoryConfiguration;
import com.telkomsigma.framework.core.api.config.EmbeddedCacheRepoConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * Library Mode / Embedded Cache Repository Configuration
 */
@Configuration
@CacheRepositoryConfiguration(rootPackage = "com.example.cache.client")
public class EmbeddedCacheRepositoryConfig extends EmbeddedCacheRepoConfigurer {
}
