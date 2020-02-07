package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.TesteIntegracaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListarCategoriaServiceIT extends TesteIntegracaoService {

    @Autowired
    private ListarCategoriaService service;

    @DisplayName("IT Listar Categoria")
    @Test
    void listarCategoriaDTO() {
        super.realizarAutenticacao();

        var categoriaDTO = service.listarDTO();
        assertTrue(categoriaDTO.size() > 0, "Tamanho de categorias diferente do esperado");

    }

    @DisplayName("IT Listar Categoria - Usuario sem categoria")
    @Test
    void listarCategoriaDTO_usuarioSemCategoria() {
        super.realizarAutenticacao("usuario_3");

        var categoriaDTO = service.listarDTO();
        assertEquals(0, categoriaDTO.size(), "Tamanho de categorias diferente do esperado");

    }

}