package com.example.band_authentication;

import com.example.band_authentication.external.oauth.UserInfo;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DummyUserInfo implements UserInfo {

    String id;
    String name;
    String gender;
    Integer birthYear;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getProvider() {
        return "Dummy";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPhone_number() {
        return null;
    }

    @Override
    public String getGender() {
        return this.gender;
    }

    @Override
    public int getBirthyear() {
        return this.birthYear;
    }
}
