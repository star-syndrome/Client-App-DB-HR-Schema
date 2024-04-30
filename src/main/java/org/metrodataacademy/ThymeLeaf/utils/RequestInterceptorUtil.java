package org.metrodataacademy.Thymeleaf.utils;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;

public class RequestInterceptorUtil implements ClientHttpRequestInterceptor {

    @SuppressWarnings("null")
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        Authentication authentication = AuthSessionUtil.getAuthentication();

        if (!request.getURI().getPath().equals("/auth/login")) {
            request.getHeaders().add(
                "Authorization", "Basic " + BasicHeaderUtil.createBasicToken(
                authentication.getName(), authentication.getCredentials().toString()));
            }

            ClientHttpResponse response = execution.execute(request, body);
            return response;
        }
}