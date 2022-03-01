package com.dina.todotogether.service;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.data.dto.ResisterValidationRequest;
import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.Role;

import java.util.Map;

public interface UserService {
    public Map<String, String> register(MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo);
    public Role saveRole(Role role);
    public AllUser getUser(String email);
    public Boolean overlappingCheck(ResisterValidationRequest memberInfo);
}
