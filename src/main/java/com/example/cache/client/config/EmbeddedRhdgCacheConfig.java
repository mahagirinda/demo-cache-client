package com.example.cache.client.config;

import com.telkomsigma.framework.core.api.config.JdgLibraryModeConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Library Mode / Embedded Cache Configuration
 */
@Configuration
@ConfigurationProperties(prefix = "jdglib")
public class EmbeddedRhdgCacheConfig extends JdgLibraryModeConfig {
}
