package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.OrigemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class InserirOrigemServiceTest {

    @Mock
    private OrigemRepository repository;

    @InjectMocks
    private InserirOrigemService service;

    @Captor
    private ArgumentCaptor<Origem> captor;

    @DisplayName("Inserir Origem DTO")
    @Test
    void inserirDTO() {
        Origem origem = new Origem();

        //given
        given(repository.save(captor.capture())).willReturn(origem);

        //when
        RequestInserirOrigemDTO request = new RequestInserirOrigemDTO();
        request.setNome("Teste");
        request.setTipoMovimentacao(TipoMovimentacao.GASTO);

        OrigemDTO origemDTO = service.inserirDTO(request);

        //then
        then(repository).should().save(any(Origem.class));
        assertNotNull(origemDTO, "Objeto não deveria ser nulo");
        assertNull(captor.getValue().getId(), "Id diferente do esperado");
        assertEquals("Teste", captor.getValue().getNome(), "Nome diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, captor.getValue().getTipoMovimentacao(), "Tipo de movimentação " +
                "diferente do esperado");

    }

}