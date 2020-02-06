package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.repository.OrigemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DeletarOrigemServiceTest {

    @Mock
    private BuscarOrigemService buscarOrigemService;

    @Mock
    private OrigemRepository repository;

    @InjectMocks
    private DeletarOrigemService service;

    @DisplayName("Deletar Origem DTO")
    @Test
    void deletarDTO() throws Exception {

        //given
        given(buscarOrigemService.buscar(anyInt())).willReturn(new Origem());

        //when
        service.deletarDTO(1);

        //then
        then(buscarOrigemService).should().buscar(anyInt());
        then(repository).should().delete(any(Origem.class));

    }

}