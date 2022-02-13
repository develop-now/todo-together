package com.dina.todotogether.api;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.service.S3Service;
import com.dina.todotogether.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RestController
public class userController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private S3Service s3Service;

    @PostMapping("/signUp")
    public void signUp (@Valid MemberSignUpRequest member, @Valid MemberInfoSignUpRequest memberInfo) throws Exception {
        MultipartFile uploadProfile = memberInfo.getUploadProfile();

        if(uploadProfile != null) {
            s3Service.UploadFile(uploadProfile);
            memberInfo.setOriginalProfile(uploadProfile.getOriginalFilename());
        }
        userServiceImpl.signUp(member, memberInfo);
    }

}
