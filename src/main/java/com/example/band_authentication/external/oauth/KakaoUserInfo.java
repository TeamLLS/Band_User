package com.example.band_authentication.external.oauth;

import java.util.Map;

public class KakaoUserInfo implements UserInfo{

    private Map<String, Object> attributes;

    private Map<String, Object> profileDatas;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.profileDatas = (Map)attributes.get("kakao_account");

        System.out.println(profileDatas);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getName() {
        return profileDatas.get("name").toString();
    }

    @Override
    public String getEmail() {
        return profileDatas.get("email").toString();
    }

    @Override
    public String getPhone_number() {
        return profileDatas.get("phone_number").toString();
    }

    @Override
    public String getGender() {
        return profileDatas.get("gender").toString();
    }

    @Override
    public int getBirthyear() {
        return Integer.parseInt(profileDatas.get("birthyear").toString());
    }
}
