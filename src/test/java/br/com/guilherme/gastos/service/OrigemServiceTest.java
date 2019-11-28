package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.OrigemNaoEncontradaException;
import br.com.guilherme.gastos.repository.OrigemRepository;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrigemServiceTest {

    @Mock
    private OrigemRepository repository;

    @Mock
    private BuscarOrigemService buscarOrigemService;

    @InjectMocks
    private OrigemService service;

    @Captor
    private ArgumentCaptor<Origem> captor;

    private Origem origem;

    @BeforeEach
    void setUp() {
        origem = new Origem();

    }
}