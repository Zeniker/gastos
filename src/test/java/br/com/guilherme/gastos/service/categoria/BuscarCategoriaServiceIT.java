package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.TesteIntegracaoService;
import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BuscarCategoriaServiceIT implements TesteIntegracaoService {

    @Autowired
    private BuscarCategoriaService buscarCategoriaService;

    @DisplayName("IT Buscar Categoria")
    @Test
    void buscar() {

        Categoria categoria = buscarCategoriaService.buscar(1);

        assertEquals(new Integer(1), categoria.getId(), "Id diferente do esperado");
        assertEquals("Comida", categoria.getDescricao(), "Descrição diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, categoria.getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");
    }

    @DisplayName("IT Buscar Categoria DTO")
    @Test
    void buscarDTO() {

        CategoriaDTO categoria = buscarCategoriaService.buscarDTO(1);

        assertEquals(new Integer(1), categoria.getId(), "Id diferente do esperado");
        assertEquals("Comida", categoria.getDescricao(), "Descrição diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, categoria.getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");
    }
}