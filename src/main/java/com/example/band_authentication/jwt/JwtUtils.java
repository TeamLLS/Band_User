package com.example.band_authentication.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    public Map<String,String> issueTokens(String username){
        String accessToken = createAccessToken(username);
        String refreshToken = createRefreshToken(username);

        Map<String, String> tokens = new HashMap<>();

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    public String createAccessToken(String username) {
        return createToken(username, "access", 60000*60);
    }

    public String createRefreshToken(String username) {
        return createToken(username, "refresh", 60000*60*12);
    }

    private String createToken(String username, String type, long expiration) {
        try {
            String secret = "testSecret";

            return JWT.create()
                    .withSubject(type + "token")
                    .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                    .withClaim("username", username)
                    .sign(Algorithm.HMAC512(type + secret));
        } catch (JWTCreationException e) {
            // 토큰 생성 실패 시 예외 처리
            e.printStackTrace();
            return null;
        }
    }


    public Map<String, String> refresh(String token){

        String username = verifyToken(token, "refresh").getClaim("username").asString();
        Map<String, String> refreshData = issueTokens(username);
        refreshData.put("username", username);

        return refreshData;
    }

    public DecodedJWT verifyToken(String token, String type) {
        String secret = "testSecret";

        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(type + secret)).build();
        return verifier.verify(token);
    }

}
