package com.example.band_authentication.user;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoChangeForm {

    private boolean emailChanged;
    private String email;

    private boolean phNumChanged;
    private String phNum;

    private boolean descriptionChanged;
    private String description;

    private boolean imageChanged;
    private MultipartFile image;
    private String imageKey;


    public boolean isEmailChanged(){
        return this.emailChanged;
    }

    public boolean isPhNumChanged(){
        return this.phNumChanged;
    }

    public boolean isDescriptionChanged(){
        return this.descriptionChanged;
    }

    public boolean isImageChanged(){
        return this.imageChanged;
    }
}
