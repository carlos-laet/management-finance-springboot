package com.desenvlaet.managementfinance.service;

import com.desenvlaet.managementfinance.model.User;

import java.util.Optional;

public interface UserService {

    User authenticate(String email, String password);

    User saveUser(User user);

    void validateEmail(String email);

    Optional<User> obterPorId(Long id);

}
