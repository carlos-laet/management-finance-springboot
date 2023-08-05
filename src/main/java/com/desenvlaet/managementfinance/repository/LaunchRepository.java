package com.desenvlaet.managementfinance.repository;

import com.desenvlaet.managementfinance.model.Launch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Long> {

    @Query(value = "select sum(l.value) from Launch l join l.user u " +
            "where u.id = :idUser and l.typeLaunch =:type group by u")
    BigDecimal getBalanceTypeLaunchAndUser(@Param("idUser") Long idUser, @Param("type") String type);
}
