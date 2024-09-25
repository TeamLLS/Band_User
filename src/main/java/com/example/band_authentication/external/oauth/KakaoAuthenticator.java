package com.example.band_authentication.external.oauth;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class KakaoAuthenticator implements OauthAuthenticator{

    @Override
    public Map<String, Object> getUserData(String accessToken){
        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v2/user/me";

        return webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public String verifyToken(String accessToken) {
        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v1/user/access_token_info";

        Map<String, Object> attributes = webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return attributes.get("id").toString();
    }
}
