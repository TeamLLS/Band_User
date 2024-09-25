package com.example.band_authentication.user;

import com.example.band_authentication.external.redis.RedisService;
import com.example.band_authentication.jwt.RefreshToken;
import com.example.band_authentication.jwt.TokenRepository;
import com.example.band_authentication.jwt.JwtUtils;
import com.example.band_authentication.user.User;
import com.example.band_authentication.external.oauth.KakaoAuthenticator;
import com.example.band_authentication.external.oauth.KakaoUserInfo;
import com.example.band_authentication.external.oauth.OauthAuthenticator;
import com.example.band_authentication.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final RedisService redisService;
    private final JwtUtils jwtUtils;


    @Autowired
    public UserService(TokenRepository tokenRepository, UserRepository userRepository, RedisService redisService, JwtUtils jwtUtils) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.redisService = redisService;
        this.jwtUtils = jwtUtils;
    }


    private User register(OauthAuthenticator authenticator, String oauthToken){

        Map<String, Object> attributes = authenticator.getUserData(oauthToken);

        User user = null;

        if (attributes != null) {
            user = new User(new KakaoUserInfo(attributes));
        }else{
            throw new RuntimeException();
        }

        userRepository.save(user);

        return user;
    }

    @Transactional
    public Map<String, String> login(String oauthToken, String provider){
        OauthAuthenticator authenticator = null;

        if(provider.equals("kakao")){
            authenticator = new KakaoAuthenticator();
        }

        String username = provider + "_" + authenticator.verifyToken(oauthToken);

        User user = userRepository.findByUsername(username).orElse(null);

        if(user==null) {
            user = register(authenticator, oauthToken);
        }

        Map<String,String> tokens = jwtUtils.issueTokens(user.getUsername());

        tokenRepository.save(new RefreshToken(user.getUsername(), tokens.get("refreshToken")));

        redisService.setValues(user.getUsername(), tokens.get("accessToken"));

        return  tokens;
    }

    @Transactional
    public Map<String, String> refresh(String token){
        Map<String, String> refreshData = jwtUtils.refresh(token);

        tokenRepository.deleteByUsername(refreshData.get("username"));
        tokenRepository.save(new RefreshToken(refreshData.get("username"), refreshData.get("refreshToken")));
        redisService.setValues(refreshData.get("username"), refreshData.get("accessToken"));

        refreshData.remove("username");

        return refreshData;
    }

    @Transactional
    public void logout(String username){
        tokenRepository.deleteByUsername(username);
        redisService.deleteValue(username);
    }
}
