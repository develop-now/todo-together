package com.dina.todotogether.service;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;

public interface UserService {
    public void signUp(MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo);
}
