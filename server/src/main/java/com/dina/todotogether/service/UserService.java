package com.dina.todotogether.service;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.data.dto.ResisterValidationRequest;
import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.Role;

public interface UserService {
    public void register(MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo);
    public Role saveRole(Role role);
    public AllUser getUser(String email);
    public Boolean overlappingCheck(ResisterValidationRequest memberInfo);
}
