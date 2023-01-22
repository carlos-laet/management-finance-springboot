package com.desenvlaet.managementfinance.service;

import com.desenvlaet.managementfinance.model.User;
import com.desenvlaet.managementfinance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Test
    public void deveValidarEmail() {
        repository.deleteAll();

        service.validateEmail("email@gmail.com");
    }

    @Test
    public void deveLancarErroQuandoExistirEmailCadastrado() {
        User usuario = User.builder().name("usuario").email("email@gmail.com").build();

        service.validateEmail("email@gmail.com");


    }
}
