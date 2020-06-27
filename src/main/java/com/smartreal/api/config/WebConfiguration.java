package com.smartreal.api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebConfiguration {
    @Bean
    public WebMvcConfigurer messageConverter(ObjectMapper objectMapper) {
        return new WebMvcConfigurer() {
            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(this.customJackson2HttpMessageConverter());
                converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
            }

            private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                return new MappingJackson2HttpMessageConverter(objectMapper);
            }
        };
    }
}
