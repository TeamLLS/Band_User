package com.example.band_authentication.user;

import com.example.band_authentication.external.oauth.UserInfo;
import com.example.band_authentication.external.redis.RedisService;
import com.example.band_authentication.external.s3.S3Service;
import com.example.band_authentication.jwt.RefreshToken;
import com.example.band_authentication.jwt.TokenRepository;
import com.example.band_authentication.jwt.JwtUtils;
import com.example.band_authentication.external.oauth.KakaoAuthenticator;
import com.example.band_authentication.external.oauth.KakaoUserInfo;
import com.example.band_authentication.external.oauth.OauthAuthenticator;
import com.example.band_authentication.user.form.SimpleUserInfoResponseForm;
import com.example.band_authentication.user.form.UserInfoChangeForm;
import com.example.band_authentication.user.form.UserInfoResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final RedisService redisService;
    private final JwtUtils jwtUtils;
    private final S3Service s3Service;

    private User register(OauthAuthenticator authenticator, String oauthToken){

        Map<String, Object> attributes = authenticator.getUserData(oauthToken);

        UserInfo userInfo;
        User user;

        if(authenticator.getProvider().equals("kakao")){
            userInfo= new KakaoUserInfo(attributes);
        }else{
            throw new RuntimeException();
        }

        user = new User(userInfo);
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

    @Transactional
    public void changeUserInfo(String username, UserInfoChangeForm changeForm){
        User user = userRepository.findByUsername(username).orElseThrow();

        System.out.println(changeForm.getImage());
        System.out.println(changeForm.getImage().isEmpty());

        String imageKey;
        if(changeForm.isImageChanged()){
            if(changeForm.getImage()==null || changeForm.getImage().isEmpty()){
                System.out.println("why?");
                imageKey = "common/profile/default.png";
            }else{
                System.out.println("why2?");
                imageKey = s3Service.saveImage("user/" + username + "/profile", "profile", changeForm.getImage());
            }

            changeForm.setImageResource(imageKey);
        }

        user.update(changeForm);
    }

    @Transactional(readOnly = true)
    public UserInfoResponseForm getUserInfo(String username){
        User user = userRepository.findByUsername(username).orElseThrow();

//        byte[] imageResource=null;
//
//        try {
//            imageResource = s3Service.loadImage(user.getImage()).readAllBytes();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        String imageResource = s3Service.getProduction() + "/" + user.getImage();

        return new UserInfoResponseForm(user, imageResource/*, imageResource*/);
    }

    @Transactional(readOnly = true)
    public SimpleUserInfoResponseForm getSimpleUserInfo(String username){
        User user = userRepository.findByUsername(username).orElseThrow();

        return new SimpleUserInfoResponseForm(user);
    }


}
