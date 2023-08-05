package com.desenvlaet.managementfinance.service;

import com.desenvlaet.managementfinance.enums.StatusLaunch;
import com.desenvlaet.managementfinance.model.Launch;
import com.desenvlaet.managementfinance.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LaunchService {

    Launch save(Launch launch);

    Launch update(Launch launch);

    void delete(Launch launch);

    List<Launch> search(Launch launchFilter);

    void updateStatus(Launch launch, StatusLaunch status);

    void validate(Launch launch);

    Optional<Launch> obterPorId(Long id);

    BigDecimal getBalanceUser(Long id);

}
