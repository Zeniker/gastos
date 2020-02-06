package br.com.guilherme.gastos.service.sessao;

import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.sessao.request.RequestRegistrarDTO;
import br.com.guilherme.gastos.exception.EmailJaCadastradoException;
import br.com.guilherme.gastos.repository.UsuarioRepository;
import br.com.guilherme.gastos.service.usuario.InserirUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class InserirUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private InserirUsuarioService service;

    @Captor
    private ArgumentCaptor<Usuario> captor;

    private RequestRegistrarDTO request;

    @BeforeEach
    void setUp() {
        service = new InserirUsuarioService(repository, passwordEncoder);

        request = new RequestRegistrarDTO();
        request.setEmail("email");
        request.setNome("nome");
        request.setSenha("senha123");
    }

    @DisplayName("Registrar novo usuário")
    @Test
    void registrar() throws Exception {

        //given
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(captor.capture())).willReturn(new Usuario());

        //when
        service.registrar(request);

        //then
        var usuarioSalvo = captor.getValue();

        then(repository).should().findByEmail("email");
        then(repository).should().save(any(Usuario.class));
        assertEquals(request.getEmail(), usuarioSalvo.getEmail(), "Email salvo diferente do esperado");
        assertEquals(request.getNome(), usuarioSalvo.getNome(), "Nome salvo diferente do esperado");
        assertTrue(passwordEncoder.matches(request.getSenha(), usuarioSalvo.getSenha()),
                "Senha gravada não condiz com o enviado");
    }

    @DisplayName("Registrar novo usuário - email já existente")
    @Test
    void registrar_emailJaExistente() throws Exception {

        //given
        given(repository.findByEmail(anyString())).willReturn(Optional.of(new Usuario()));

        assertThrows(EmailJaCadastradoException.class, () -> service.registrar(request));
    }
}