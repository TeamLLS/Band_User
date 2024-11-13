package com.example.band_authentication;


import com.example.band_authentication.user.User;
import com.example.band_authentication.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DummyUtil {

    private final UserRepository userRepository;

    @Transactional
    @PostConstruct
    public void makeDummy(){
        String[] ids = {"userA", "userB", "userC", "userD", "userE", "userF", "userG"};
        String[] names = {"허연준", "임윤빈", "권미르", "최은", "하도준", "한지수", "이승호"};
        String[] genders = {"male", "female", "male", "female", "male", "female", "male"};
        Integer[] birthYears = {2003, 2002, 2003, 2003, 2004, 2003, 2004};


        for(int i=0; i<7; i++){
            userRepository.save(new User(new DummyUserInfo(ids[i], names[i], genders[i], birthYears[i])));
        }

    }



}
