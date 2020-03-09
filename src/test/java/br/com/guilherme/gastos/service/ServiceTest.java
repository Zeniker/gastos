package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.TesteUnitario;
import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ServiceTest implements TesteUnitario {

    @Mock
    private SessaoService sessaoService;

    protected void setUp() {
        given(sessaoService.getUsuarioAtual()).willReturn(new Usuario());
    }
}
