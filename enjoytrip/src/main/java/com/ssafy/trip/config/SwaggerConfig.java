package com.ssafy.trip.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "SSAFY TRIP API 명세서", description = "API 명세서 테스트 입니다.", version = "v1"))
public class SwaggerConfig {

    // 관련된 API들의 grouping
    @Bean
    GroupedOpenApi attractionOpenApi() {
        String[] paths = { "/api/v1/attractions/**" };
        return GroupedOpenApi.builder().group("Attraction 관련 API").pathsToMatch(paths).build();
    }
    
    @Bean
    GroupedOpenApi likeOpenApi() {
        String[] paths = { "/api/v1/likes/**" };
        return GroupedOpenApi.builder().group("like 관련 API").pathsToMatch(paths).build();
    }
    
    @Bean
    GroupedOpenApi groupOpenApi() {
        String[] paths = { "/api/v1/groups/**" };
        return GroupedOpenApi.builder().group("group 관련 API").pathsToMatch(paths).build();
    }

    @Bean
    GroupedOpenApi userOpenApi() {
        String[] paths = { "/api/v1/users/**" };
        return GroupedOpenApi.builder().group("User 관련 API").pathsToMatch(paths).build();
    }
    
    @Bean
    GroupedOpenApi authOpenApi() {
        String[] paths = { "/api/v1/auth/**" };
        return GroupedOpenApi.builder().group("Auth 관련 API").pathsToMatch(paths).build();
    }
    
    @Bean
    GroupedOpenApi postOpenApi() {
        String[] paths = { "/api/v1/posts/**" };
        return GroupedOpenApi.builder().group("Post 관련 API").pathsToMatch(paths).build();
    }

    @Bean
    GroupedOpenApi commentOpenApi() {
        String[] paths = { "/api/v1/comments/**" };
        return GroupedOpenApi.builder().group("Comment 관련 API").pathsToMatch(paths).build();
    }
    
    @Bean
    GroupedOpenApi planOpenApi() {
        String[] paths = { "/api/v1/plans/**" };
        return GroupedOpenApi.builder().group("Plan 관련 API").pathsToMatch(paths).build();
    }
    
    @Bean
    GroupedOpenApi otherOpenApi() {
        String[] paths = { "/api/etc/**" };
        return GroupedOpenApi.builder().group("기타 API").pathsToMatch(paths).build();
    }
}
