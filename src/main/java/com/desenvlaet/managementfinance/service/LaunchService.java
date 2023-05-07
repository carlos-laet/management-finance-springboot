package com.desenvlaet.managementfinance.service;

import com.desenvlaet.managementfinance.enums.StatusLaunch;
import com.desenvlaet.managementfinance.model.Launch;
import com.desenvlaet.managementfinance.model.User;

import java.util.List;

public interface LaunchService {

    Launch save(Launch launch);

    Launch update(Launch launch);

    void delete(Launch launch);

    List<Launch> search(Launch launchFilter);

    void updateStatus(Launch launch, StatusLaunch status);

    void validate(Launch launch);

}
