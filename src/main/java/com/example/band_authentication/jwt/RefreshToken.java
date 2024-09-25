package com.example.band_authentication.jwt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String token;

    public RefreshToken(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
