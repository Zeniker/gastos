package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.TesteIntegracaoService;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletarCategoriaServiceIT extends TesteIntegracaoService {

    @Autowired
    private InserirCategoriaService inserirCategoriaService;

    @Autowired
    private ListarCategoriaService listarCategoriaService;

    @Autowired
    private DeletarCategoriaService deletarCategoriaService;

    @DisplayName("IT Deletar Categoria")
    @Test
    void deletarDTO() throws CategoriaNaoEncontradaException {
        super.realizarAutenticacao();

        var requestInserir = new RequestInserirCategoriaDTO();
        requestInserir.setDescricao("Teste");
        requestInserir.setTipoMovimentacao(TipoMovimentacao.GASTO);
        var categoriaInserida = inserirCategoriaService.inserirDTO(requestInserir);

        int quantidadeCategorias = listarCategoriaService.listarDTO().size();

        deletarCategoriaService.deletarDTO(categoriaInserida.getId());

        int novaQuantidadeCategorias = listarCategoriaService.listarDTO().size();

        assertEquals(quantidadeCategorias - 1, novaQuantidadeCategorias,
                "A quantidade de categorias não foi diminuída");

    }

    @DisplayName("IT Deletar Categoria - Usuário diferente")
    @Test
    void deletarDTO_usuarioDiferente() {
        //Loga com usuario
        super.realizarAutenticacao();

        var requestInserir = new RequestInserirCategoriaDTO();
        requestInserir.setDescricao("Teste");
        requestInserir.setTipoMovimentacao(TipoMovimentacao.GASTO);
        var categoriaInserida = inserirCategoriaService.inserirDTO(requestInserir);

        int quantidadeCategorias = listarCategoriaService.listarDTO().size();

        //Loga com usuario_3
        super.realizarAutenticacao("usuario_3");

        assertThrows(CategoriaNaoEncontradaException.class,
                () -> deletarCategoriaService.deletarDTO(categoriaInserida.getId())
        );

        //Volta para usuario
        super.realizarAutenticacao();
        int novaQuantidadeCategorias = listarCategoriaService.listarDTO().size();

        assertEquals(quantidadeCategorias, novaQuantidadeCategorias,
                "A quantidade de categorias não deveria ter sido alterada");

    }
}