package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.GastosApplicationTest;
import br.com.guilherme.gastos.TesteIntegracaoService;
import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { GastosApplicationTest.class })
@ActiveProfiles("test")
class InserirCategoriaServiceIT implements TesteIntegracaoService {

    @Autowired
    private InserirCategoriaService inserirCategoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @DisplayName("IT Inserir Categoria")
    @Test
    void inserirCategoriaDTO() {
        RequestInserirCategoriaDTO request = new RequestInserirCategoriaDTO();
        request.setDescricao("Teste");
        request.setTipoMovimentacao(TipoMovimentacao.GANHO);

        CategoriaDTO categoriaSalva = inserirCategoriaService.inserirDTO(request);

        Optional<Categoria> categoria = categoriaRepository.findById(categoriaSalva.getId());

        assertTrue(categoria.isPresent(), "Categoria não encontrada");
    }
}