package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.exception.OrigemNaoEncontradaException;
import br.com.guilherme.gastos.repository.OrigemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrigemService {

    private OrigemRepository origemRepository;

    public OrigemService(OrigemRepository origemRepository) {

        this.origemRepository = origemRepository;
    }

    @Transactional
    public Origem inserir(RequestInserirOrigemDTO request) {

        Origem origem = new Origem();
        origem.setNome(request.getNome());

        return origemRepository.save(origem);
    }

    public Origem buscar(Integer id) {

        Optional<Origem> optionalOrigem = origemRepository.findById(id);

        return optionalOrigem.orElseThrow(OrigemNaoEncontradaException::new);
    }

    public List<Origem> listar() {

        return origemRepository.findAll();
    }

    public Origem alterar(Integer id, RequestAlterarOrigemDTO request) {

        Origem origem = buscar(id);

        origem.setNome(request.getNome());

        return origemRepository.save(origem);
    }

    public void deletar(Integer id) {

        Origem origem = buscar(id);

        origemRepository.delete(origem);

    }
}
