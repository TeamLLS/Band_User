package com.example.band_authentication.jwt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    public Optional<RefreshToken> findByToken(String token);

    public void deleteByUsername(String username);
}
