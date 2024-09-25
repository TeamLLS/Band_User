package com.example.band_authentication.external.oauth;

public interface UserInfo {

    public String getId();

    public String getProvider();

    public String getName();

    public String getEmail();

    public String getPhone_number();

    public String getGender();

    public int getBirthyear();
}
