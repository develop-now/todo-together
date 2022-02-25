package com.dina.todotogether.service;

import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.data.dto.ResisterValidationRequest;
import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.MemberInfo;
import com.dina.todotogether.data.entity.Role;
import com.dina.todotogether.data.entity.RoleType;
import com.dina.todotogether.repo.MemberInfoRepo;
import com.dina.todotogether.repo.RoleRepo;
import com.dina.todotogether.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final MemberInfoRepo memberInfoRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AllUser user = userRepo.findByEmail(email);

        if(user == null) {
            log.error("email이 일치하는 user가 DB에 존재하지 않습니다.");
            throw new UsernameNotFoundException("email이 일치하는 user가 DB에 존재하지 않습니다.");
        }else {
            log.info("DB에서 조회된 member email : {}", email);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRName().toString()));
        });

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public void register(MemberSignUpRequest member, MemberInfoSignUpRequest memberInfo) {

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        List<Role> userRole = new ArrayList<>();
        userRole.add(roleRepo.findByrName(RoleType.ROLE_USER));
        member.setRoles(userRole);

        AllUser entityUser = member.toEntity();
        AllUser saveMember = userRepo.save(entityUser);

        MultipartFile uploadProfile = memberInfo.getUploadProfile();
        if(uploadProfile != null) {
            try {
                s3Service.UploadFile(uploadProfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            memberInfo.setOriginalProfile(uploadProfile.getOriginalFilename());
        }
        MemberInfo entityMemberInfo = memberInfo.toEntity(entityUser);
        MemberInfo saveMemberInfo = memberInfoRepo.save(entityMemberInfo);

    }

    @Override
    public Boolean overlappingCheck(ResisterValidationRequest memberInfoCheck) {

        String email = memberInfoCheck.getEmail();
        String nickname = memberInfoCheck.getNickname();
        if(email != null) {
            AllUser emailCheck = userRepo.findByEmail(email);
            return emailCheck == null;
        }
        if(nickname != null) {
            MemberInfo nicknameCheck = memberInfoRepo.findByNickname(nickname);
            return nicknameCheck == null;
        }
        return true;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public AllUser getUser(String email) {
        log.info("Fetching User : {}", email);
        return userRepo.findByEmail(email);
    }

}
