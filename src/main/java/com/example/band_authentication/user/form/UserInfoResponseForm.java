package com.example.band_authentication.user.form;


import com.example.band_authentication.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class UserInfoResponseForm {

    private Long userId;
    private String username;
    private String email;
    private String name;
    private String gender;
    private Integer age;
    private String phNum;

    private String description;
    private String image;

    private ZonedDateTime time;
    //private byte[] image;

    public UserInfoResponseForm(User user, String imageResource/*, byte[] image*/) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.gender = user.getGender();
        this.age = Year.now().getValue() - user.getBirthYear();
        this.phNum = user.getPhNum();
        this.description = user.getDescription();
        this.image = imageResource;
        this.time = Instant.now().atZone(ZoneId.of("Asia/Seoul"));
        //this.image = user.getImage();
        //this.image = image;
    }
}
