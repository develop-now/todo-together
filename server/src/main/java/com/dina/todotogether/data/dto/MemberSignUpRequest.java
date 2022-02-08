package com.dina.todotogether.data.dto;

import com.dina.todotogether.data.entity.AllUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class MemberSignUpRequest {

    private String email;
    private String password;

    public AllUser toEntity() {
        return AllUser.builder()
                .email(email)
                .password(password)
                .build();
    }
}
