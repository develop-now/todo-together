package com.dina.todotogether.data.dto;

import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberSignUpRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    private Collection<Role> roles= new HashSet<>();

    public AllUser toEntity() {
        return AllUser.builder()
                .email(email)
                .password(password)
                .roles(roles)
                .build();
    }
}
