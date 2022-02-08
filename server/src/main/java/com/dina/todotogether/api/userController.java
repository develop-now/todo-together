package com.dina.todotogether.api;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class userController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/signUp")
    public void signUp (MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo) {
        userServiceImpl.signUp(member, memberInfo);
    }
}
