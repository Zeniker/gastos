package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.OrigemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AlterarOrigemServiceTest {

    @Mock
    private OrigemRepository repository;

    @Mock
    private BuscarOrigemService buscarOrigemService;

    @InjectMocks
    private AlterarOrigemService service;

    @DisplayName("Alterar Origem DTO")
    @Test
    void alterarDTO() throws Exception {

        //given
        Origem origem = new Origem();
        origem.setId(1);
        origem.setNome("Teste");
        origem.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarOrigemService.buscar(anyInt())).willReturn(origem);
        given(repository.save(any(Origem.class))).willReturn(origem);

        //when
        RequestAlterarOrigemDTO request = new RequestAlterarOrigemDTO();
        request.setNome("TesteAlterado");

        OrigemDTO origemAlterada = service.alterarDTO(1, request);

        //then
        then(repository).should().save(any(Origem.class));
        then(buscarOrigemService).should().buscar(anyInt());
        assertNotNull(origemAlterada, "Objeto não deveria ser nulo");
        assertEquals(new Integer(1), origemAlterada.getId(), "Id diferente do esperado");
        assertEquals("TesteAlterado", origemAlterada.getNome(), "Nome diferente do esperado");
        assertEquals(TipoMovimentacao.GANHO, origemAlterada.getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");

    }

}