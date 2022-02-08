package com.dina.todotogether.service;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.MemberInfo;
import com.dina.todotogether.repo.MemberInfoRepo;
import com.dina.todotogether.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final MemberInfoRepo memberInfoRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, MemberInfoRepo memberInfoRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.memberInfoRepo = memberInfoRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        AllUser saveMember = member.toEntity();
        MemberInfo saveMemberInfo = memberInfo.toEntity(saveMember);
        AllUser memberResult = userRepo.save(saveMember);
        MemberInfo memberInfoResult = memberInfoRepo.save(saveMemberInfo);
    }
}
