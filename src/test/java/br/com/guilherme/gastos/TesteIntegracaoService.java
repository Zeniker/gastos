package br.com.guilherme.gastos;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { GastosApplicationTest.class })
@ActiveProfiles("test")
@Tag("integracao")
public interface TesteIntegracaoService {
}
