package com.desenvlaet.managementfinance.service.impl;

import com.desenvlaet.managementfinance.exception.RuleBusinessException;
import com.desenvlaet.managementfinance.model.User;
import com.desenvlaet.managementfinance.repository.UserRepository;
import com.desenvlaet.managementfinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User authenticate(String email, String password) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public void validateEmail(String email) {
        boolean exists = repository.existsByEmail(email);
        if (exists) {
            throw new RuleBusinessException("Já existe um usuário cadastrado com este email");
        }
    }
}
