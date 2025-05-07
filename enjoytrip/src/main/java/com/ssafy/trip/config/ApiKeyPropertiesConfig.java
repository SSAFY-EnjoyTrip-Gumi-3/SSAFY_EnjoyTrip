package com.ssafy.trip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "api")
@Getter @Setter
public class ApiKeyPropertiesConfig {
	@Value("${api.vworld-key}")
	private String vworldKey;
	@Value("${api.service-id}")
	private String serviceId;
	@Value("${api.security}")
    private String security;
	@Value("${api.data-key}")
    private String dataKey;
}
