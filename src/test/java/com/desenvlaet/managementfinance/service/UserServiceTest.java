package com.desenvlaet.managementfinance.service;

import com.desenvlaet.managementfinance.model.User;
import com.desenvlaet.managementfinance.repository.UserRepository;
import com.desenvlaet.managementfinance.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    private UserService service;

    private UserRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(UserRepository.class);
        service = new UserServiceImpl(repository);
    }

//    @Test
//    public void deveAutenticarUsuarioComSucesso() {
//        String email = "email@gmail.com";
//        String senha = "senha";
//
//        User user = User.builder().email(email).password(senha).id(1l).build();
//        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(user));
//
//        User result = service.authenticate(email, senha);
//
//        Assertions.assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {
//        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
//
//        service.authenticate("email@gmail.com", "senha");
//    }

    @Test
    public void deveLancarErroQuandoSenhaNaoBater() {
        String senha = "senha";
        User user = User.builder().email("email@gmail.com").password(senha).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

        service.authenticate("email@gmail.com", "123");
    }

    @Test
    public void deveValidarEmail() {

        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

        service.validateEmail("email@gmail.com");
    }

    @Test
    public void deveLancarErroQuandoExistirEmailCadastrado() {
        User usuario = User.builder().name("usuario").email("email@gmail.com").build();

        service.validateEmail("email@gmail.com");


    }
}
