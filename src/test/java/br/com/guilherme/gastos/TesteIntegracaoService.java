package br.com.guilherme.gastos;

import br.com.guilherme.gastos.service.sessao.SessaoService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { GastosApplicationTest.class })
@TestPropertySource(locations = "classpath:application-test.properties")
@Tag("integracao")
public abstract class TesteIntegracaoService {

    @Autowired
    private SessaoService sessaoService;

    protected void realizarAutenticacao(){

        this.realizarAutenticacao("usuario");
    }

    protected void realizarAutenticacao(String usuario){

        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
