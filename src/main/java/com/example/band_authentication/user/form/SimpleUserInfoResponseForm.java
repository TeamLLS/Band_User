package com.example.band_authentication.user.form;

import com.example.band_authentication.user.User;
import lombok.Getter;

@Getter
public class SimpleUserInfoResponseForm {
    private String username;
    private Integer birthYear;
    private String name;
    private String gender;

    public SimpleUserInfoResponseForm(User user) {
        this.username = user.getUsername();
        this.birthYear = user.getBirthYear();
        this.name = user.getName();
        this.gender = user.getGender();
    }
}
