package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.TesteIntegracaoService;
import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuscarCategoriaServiceIT extends TesteIntegracaoService {

    @Autowired
    private BuscarCategoriaService buscarCategoriaService;

    @BeforeEach
    void setUp() {
        super.realizarAutenticacao();
    }

    @DisplayName("IT Buscar Categoria")
    @Test
    void buscar() throws Exception {

        Categoria categoria = buscarCategoriaService.buscar(1);

        assertEquals(1, categoria.getId().intValue(), "Id diferente do esperado");

    }

    @DisplayName("IT Buscar Categoria - Usuario diferente")
    @Test
    void buscar_usuarioDiferente() {

        super.realizarAutenticacao("usuario_2");
        assertThrows(CategoriaNaoEncontradaException.class, () -> buscarCategoriaService.buscar(1));

    }
}