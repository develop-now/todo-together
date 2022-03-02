package com.dina.todotogether.data.dto;

import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberSignUpRequest {

    @Email
    @NotBlank(message = "email은 필수 입력값이며, null이 불가능합니다.")
    private String email;

    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[0-9]).{4,}$", message = "비밀번호는 문자열4개 이상, 소문자와 숫자를 조합해야합니다.")
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
