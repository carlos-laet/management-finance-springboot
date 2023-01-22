package com.desenvlaet.managementfinance.repository;

import com.desenvlaet.managementfinance.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;


    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        User user = User.builder().name("user").email("user@gmail.com").build();
        repository.save(user);

        boolean result = repository.existsByEmail("user@gmail.com");

        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastroComOEmail() {
        boolean result = repository.existsByEmail("user@gmail.com");

        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        User user = createUser();

        User userSave = repository.save(user);

        Assertions.assertThat(userSave.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUsuarioPorEmail() {
        User user = createUser();
        repository.save(user);

        Optional<User> result = repository.findByEmail("nome@gmail.com");

        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
        Optional<User> result = repository.findByEmail("nome@gmail.com");

        Assertions.assertThat(result.isPresent()).isFalse();
    }

    public static User createUser() {
        return User.builder().name("nome").email("nome@gmail.com").password("senha").build();
    }
}
