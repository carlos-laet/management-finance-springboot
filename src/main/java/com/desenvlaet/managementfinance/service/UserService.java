package com.desenvlaet.managementfinance.service;

import com.desenvlaet.managementfinance.model.User;

public interface UserService {

    User authenticate(String email, String password);

    User saveUser(User user);

    void validateEmail(String email);

}
