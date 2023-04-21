package com.desenvlaet.managementfinance.repository;

import com.desenvlaet.managementfinance.model.Launch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Long> {
}
