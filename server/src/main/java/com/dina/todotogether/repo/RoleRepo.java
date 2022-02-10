package com.dina.todotogether.repo;

import com.dina.todotogether.data.entity.Role;
import com.dina.todotogether.data.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public interface RoleRepo extends JpaRepository<Role, Long> {
    public Role findByrName(RoleType rName);
}
