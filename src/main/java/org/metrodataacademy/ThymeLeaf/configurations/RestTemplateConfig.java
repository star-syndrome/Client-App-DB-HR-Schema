package org.metrodataacademy.Thymeleaf.configurations;

import java.util.ArrayList;
import java.util.List;

import org.metrodataacademy.Thymeleaf.utils.RequestInterceptorUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(new RequestInterceptorUtil());
        restTemplate.setInterceptors(interceptors);
        
        return restTemplate;
    }
}