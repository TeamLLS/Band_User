package com.example.band_authentication.user.form;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoChangeForm {

    private Boolean emailChanged;
    private String email;

    private Boolean phNumChanged;
    private String phNum;

    private Boolean descriptionChanged;
    private String description;

    private Boolean imageChanged;
    private MultipartFile image;
    private String imageResource;


    public boolean isEmailChanged(){
        return (emailChanged!=null)?emailChanged:false;
    }

    public boolean isPhNumChanged(){
        return (phNumChanged!=null)?phNumChanged:false;
    }

    public boolean isDescriptionChanged(){
        return (descriptionChanged!=null)?descriptionChanged:false;
    }

    public boolean isImageChanged(){
        return (imageChanged!=null?emailChanged:false);
    }
}
