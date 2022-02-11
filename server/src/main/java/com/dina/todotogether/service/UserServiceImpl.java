package com.dina.todotogether.service;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.MemberInfo;
import com.dina.todotogether.data.entity.Role;
import com.dina.todotogether.data.entity.RoleType;
import com.dina.todotogether.repo.MemberInfoRepo;
import com.dina.todotogether.repo.RoleRepo;
import com.dina.todotogether.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final MemberInfoRepo memberInfoRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, MemberInfoRepo memberInfoRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.memberInfoRepo = memberInfoRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo) {

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        List<Role> userRole = new ArrayList<>();
        userRole.add(roleRepo.findByrName(RoleType.ROLE_USER));
        member.setRoles(userRole);

        AllUser entityUser = member.toEntity();
        AllUser saveMember = userRepo.save(entityUser);

        MemberInfo entityMemberInfo = memberInfo.toEntity(entityUser);
        MemberInfo saveMemberInfo = memberInfoRepo.save(entityMemberInfo);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }
}
