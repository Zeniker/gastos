package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.repository.OrigemRepository;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrigemService {

    private OrigemRepository origemRepository;

    private BuscarOrigemService buscarOrigemService;

    public OrigemService(OrigemRepository origemRepository, BuscarOrigemService buscarOrigemService) {

        this.origemRepository = origemRepository;
        this.buscarOrigemService = buscarOrigemService;
    }



    public List<Origem> listar() {

        return origemRepository.findAll();
    }

    @Transactional
    public Origem alterar(Integer id, RequestAlterarOrigemDTO request) {

        Origem origem = buscarOrigemService.buscar(id);

        origem.setNome(request.getNome());

        return origemRepository.save(origem);
    }

    @Transactional
    public void deletar(Integer id) {

        Origem origem = buscarOrigemService.buscar(id);

        origemRepository.delete(origem);

    }
}
