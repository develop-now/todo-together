package com.dina.todotogether.repo;

import com.dina.todotogether.data.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepo extends JpaRepository<MemberInfo, Long> {
}
