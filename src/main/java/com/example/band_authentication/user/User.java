package com.example.band_authentication.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import com.example.band_authentication.external.oauth.KakaoUserInfo;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String role;

    private String name;
    private String gender;
    private int birthyear;
    private String phNum;

    private String description;
    private String image;

    public User(KakaoUserInfo userInfo) {
        this.username = userInfo.getProvider() + "_" + userInfo.getId();
        this.email = userInfo.getEmail();
        this.role = "NORMAL";
        this.name = userInfo.getName();
        this.gender = userInfo.getGender();
        this.phNum = userInfo.getPhone_number();
        this.birthyear = userInfo.getBirthyear();
        this.description = null;
        this.image = null;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhNum() {
        return phNum;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void changeRole(String role){
        this.role = role;
    }

    public void changeDescription(String description){
        this.description = description;
    }

    public void changeImage(String image){
        this.image = image;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changePhNum(String phNum){
        this.phNum = phNum;
    }
}
