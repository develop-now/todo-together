package com.dina.todotogether.data.dto;

import com.dina.todotogether.data.entity.AllUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberSignUpRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    public AllUser toEntity() {
        return AllUser.builder()
                .email(email)
                .password(password)
                .build();
    }
}
