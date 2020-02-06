package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.*;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ManutencaoMovimentacao {

    protected BuscarOrigemService buscarOrigemService;
    protected BuscarCategoriaService buscarCategoriaService;

    Categoria getCategoriaMovimentacao(Integer idCategoria, TipoMovimentacao tipoMovimentacao)
            throws CategoriaNaoCompativelException, CategoriaNaoEncontradaException {

        Categoria categoria = buscarCategoriaService.buscar(idCategoria);

        if(categoria.getTipoMovimentacao() != tipoMovimentacao)
            throw new CategoriaNaoCompativelException();

        return categoria;
    }

    protected Origem getOrigemMovimentacao(Integer idOrigem, TipoMovimentacao tipoMovimentacao)
            throws OrigemNaoCompativelException, OrigemNaoEncontradaException {

        Origem origem = buscarOrigemService.buscar(idOrigem);

        if(origem.getTipoMovimentacao() != tipoMovimentacao)
            throw new OrigemNaoCompativelException();

        return origem;
    }

}
