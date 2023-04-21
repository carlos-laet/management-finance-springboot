package com.desenvlaet.managementfinance.service.impl;

import com.desenvlaet.managementfinance.exception.ErrorAuthenticateException;
import com.desenvlaet.managementfinance.exception.RuleBusinessException;
import com.desenvlaet.managementfinance.model.User;
import com.desenvlaet.managementfinance.repository.UserRepository;
import com.desenvlaet.managementfinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User authenticate(String email, String password) {
        Optional<User> user = repository.findByEmail(email);

        if (!user.isPresent()) {
            throw new ErrorAuthenticateException("Usuário não encontrado");
        }

        if (!user.get().getPassword().equals(password)) {
            throw new ErrorAuthenticateException("Senha inválida");
        }

        return user.get();
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        validateEmail(user.getEmail());
        return repository.save(user);
    }

    @Override
    public void validateEmail(String email) {
        boolean exists = repository.existsByEmail(email);
        if (exists) {
            throw new RuleBusinessException("Já existe um usuário cadastrado com este email");
        }
    }
}
