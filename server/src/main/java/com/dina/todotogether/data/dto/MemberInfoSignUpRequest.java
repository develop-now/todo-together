package com.dina.todotogether.data.dto;

import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.MemberInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberInfoSignUpRequest {

    @NotBlank(message = "닉네임은 필수 입력값이며, null이 불가능합니다.")
    private String nickname;

    @NotBlank(message = "이름은 필수 입력값이며, null이 불가능합니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력값이며, null이 불가능합니다.")
    private String phone;

    @Email
    @NotBlank(message = "backupEmail은 필수 입력값이며, null이 불가능합니다.")
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
