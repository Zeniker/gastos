package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.TesteIntegracaoService;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AlterarCategoriaServiceIT extends TesteIntegracaoService {

    @Autowired
    private BuscarCategoriaService buscarCategoriaService;

    @Autowired
    private AlterarCategoriaService alterarCategoriaService;

    @DisplayName("IT Alterar Categoria")
    @Test
    void alterarDTO() throws Exception {

        super.realizarAutenticacao();
        var categoria = buscarCategoriaService.buscar(1);

        var request = new RequestAlterarCategoriaDTO();
        request.setDescricao("Nova descricao");

        alterarCategoriaService.alterarDTO(1, request);
        var categoriaAlterada = buscarCategoriaService.buscar(1);
        assertNotEquals(categoria.getDescricao(), categoriaAlterada.getDescricao(),
                "Descrição da categoria deveria ter sido alterada");

    }

    @DisplayName("IT Alterar Categoria - Usuario Diferente")
    @Test
    void alterarDTO_usuarioDiferente() throws Exception {

        //Loga com usuario
        super.realizarAutenticacao();
        var categoria = buscarCategoriaService.buscar(1);

        var request = new RequestAlterarCategoriaDTO();
        request.setDescricao("Nova descricao");

        //Loga com usuario_2
        super.realizarAutenticacao("usuario_2");
        assertThrows(CategoriaNaoEncontradaException.class, () -> alterarCategoriaService.alterarDTO(1, request));

        //Volta para usuario
        super.realizarAutenticacao("usuario");
        var categoriaAlterada = buscarCategoriaService.buscar(1);
        assertEquals(categoria.getDescricao(), categoriaAlterada.getDescricao(),
                "Descrição da categoria não deveria ter sido alterada");

    }
}