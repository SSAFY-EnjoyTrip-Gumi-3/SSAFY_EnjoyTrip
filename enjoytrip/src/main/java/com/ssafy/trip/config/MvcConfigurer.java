package com.ssafy.trip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        // "/" 요청이 들어오면 attractionTest.jsp 로 포워딩
	        registry.addViewController("/").setViewName("forward:/WEB-INF/views/attractionTest.jsp");
	    }
}
