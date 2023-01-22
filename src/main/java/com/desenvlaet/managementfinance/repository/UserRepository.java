package com.desenvlaet.managementfinance.repository;

import com.desenvlaet.managementfinance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
