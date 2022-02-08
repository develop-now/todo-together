package com.dina.todotogether.data.dto;

import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.MemberInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberInfoSignUpRequest {

    private String nickname;
    private String name;
    private String phone;
    private String backupEmail;
    private String originalProfile;
    private String storedProfile;
    private MultipartFile uploadProfile;

    public MemberInfo toEntity(AllUser allUser) {
        return MemberInfo.builder()
                .allUser(allUser)
                .nickname(nickname)
                .name(name)
                .phone(phone)
                .backupEmail(backupEmail)
                .originalProfile(originalProfile)
                .storedProfile(storedProfile)
                .build();
    }
}
