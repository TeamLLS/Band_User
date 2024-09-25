package com.example.band_authentication.external.oauth;

import java.util.Map;

public interface OauthAuthenticator {

    public Map<String, Object> getUserData(String accessToken);

    public String verifyToken(String accessToken);
}
