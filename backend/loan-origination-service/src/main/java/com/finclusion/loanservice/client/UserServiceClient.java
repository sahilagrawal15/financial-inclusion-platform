package com.finclusion.loanservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient webClient;

    public boolean verifyUser(String userId, String token) {
        return webClient.get()
                .uri("http://localhost:8080/api/v1/user/verify/" + userId)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .blockOptional()
                .orElse(false);
    }

}
