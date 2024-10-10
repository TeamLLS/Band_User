package com.example.band_authentication.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

import java.time.Year;

@Getter
@Setter
public class UserInfoResponseForm {

    private String username;
    private String email;
    private String name;
    private String gender;
    private Integer age;
    private String phNum;

    private String description;
    private InputStreamResource image;

    public UserInfoResponseForm(User user, InputStreamResource image) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.gender = user.getGender();
        this.age = Year.now().getValue() - user.getBirthyear();
        this.phNum = user.getPhNum();
        this.description = user.getDescription();
        this.image = image;
    }
}
