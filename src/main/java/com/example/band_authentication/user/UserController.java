package com.example.band_authentication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/health_check")
    public ResponseEntity<?> welcome(){
        return ResponseEntity.ok().build();
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestHeader String token, @RequestHeader String provider){
        Map<String,String> tokens = userService.login(token, provider);

        return ResponseEntity.ok().body(tokens);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader String token){
        Map<String, String> tokens = userService.refresh(token);

        return ResponseEntity.ok().body(tokens);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader String username){
        userService.logout(username);

        return ResponseEntity.ok().build();
    }
}
