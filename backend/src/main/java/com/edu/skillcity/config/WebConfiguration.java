package com.edu.skillcity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${uploadPathFull}")
    private String uploadPath;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
            {
                    "classpath:/static/uploads",
            };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**", "/uploads/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
                .addResourceLocations("file:///" + uploadPath + "/")
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
        ;
    }
}
