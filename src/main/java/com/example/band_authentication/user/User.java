package com.example.band_authentication.user;


import com.example.band_authentication.external.oauth.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String name;
    private String gender;
    private Integer birthyear;
    private String phNum;

    private String description;
    private String image;

    private LocalDateTime createdAt;

    public User(UserInfo userInfo) {
        this.username = userInfo.getProvider() + "_" + userInfo.getId();
        this.email = userInfo.getEmail();
        this.name = userInfo.getName();
        this.gender = userInfo.getGender();
        this.phNum = userInfo.getPhone_number();
        this.birthyear = userInfo.getBirthyear();
        this.description = null;
        this.image = null;
        this.createdAt = LocalDateTime.now();
    }

    public void update(UserInfoChangeForm changeForm){

        if(changeForm.isImageChanged()){
            this.image = changeForm.getImageKey();
        }
        if(changeForm.isDescriptionChanged()){
            this.description=changeForm.getDescription();
        }
        if(changeForm.isEmailChanged()){
            this.email=changeForm.getEmail();
        }
        if(changeForm.isPhNumChanged()){
            this.phNum= changeForm.getPhNum();
        }
    }
}
