package com.dina.todotogether.repo;

import com.dina.todotogether.data.entity.AllUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AllUser, Long> {
}
