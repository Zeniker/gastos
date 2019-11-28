package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DeletarMovimentacaoServiceTest {

    @Mock
    private BuscarMovimentacaoService buscarMovimentacaoService;

    @Mock
    private MovimentacaoRepository repository;

    @InjectMocks
    private DeletarMovimentacaoService service;

    @DisplayName("Deletar movimentação")
    @Test
    void deletarMovimentacao() {
        //given
        Movimentacao movimentacao = new Movimentacao();
        given(buscarMovimentacaoService.buscarMovimentacao(anyInt())).willReturn(movimentacao);

        //when
        ResponseDTO responseDTO =  service.deletarMovimentacaoDTO(1);

        //then
        then(buscarMovimentacaoService).should().buscarMovimentacao(anyInt());
        then(repository).should().delete(movimentacao);
        assertNotNull(responseDTO, "ResponseDTO não deveria estar nulo");
    }

}