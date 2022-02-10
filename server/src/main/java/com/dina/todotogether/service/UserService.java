package com.dina.todotogether.service;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.data.entity.Role;

public interface UserService {
    public void signUp(MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo);
    public Role saveRole(Role role);
}
