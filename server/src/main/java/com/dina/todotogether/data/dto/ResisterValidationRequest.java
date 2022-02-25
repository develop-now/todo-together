package com.dina.todotogether.data.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ResisterValidationRequest {

    @Email
    private String email;

    private String nickname;
}
