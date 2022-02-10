package com.dina.todotogether.data.dto;

import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.MemberInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class MemberInfoSignUpRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
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
