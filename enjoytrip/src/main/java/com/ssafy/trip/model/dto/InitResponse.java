package com.ssafy.trip.model.dto;

import java.util.List;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ssafy.trip.config.ApiKeyPropertiesConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EnableConfigurationProperties(ApiKeyPropertiesConfig.class)
public class InitResponse {
	 private final List<Sido> sidos;
	 private final List<ContentType> contentTypes;
	 private final String keyVworld;
	 private final String keySgisServiceId;
	 private final String keySgisSecurity;
	 private final String keyData;
}
