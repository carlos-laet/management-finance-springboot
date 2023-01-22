//package com.desenvlaet.managementfinance.repository;
//
//import com.desenvlaet.managementfinance.model.User;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Test
//    public void devVerificarAExistenciaDeUmEmail() {
//        User user = User.builder().name("user").email("user@gmail.com").build();
//        repository.save(user);
//
//        boolean result = repository.existsByEmail("user@gmail.com");
//
//        Assertions.assertThat(result).isTrue();
//    }
//}
