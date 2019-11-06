package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.dto.ganho.GanhoDTO;
import br.com.guilherme.gastos.dto.ganho.request.RequestAlterarGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.request.RequestInserirGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseAlterarGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseConsultarGanhoAnoMesDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseInserirGanhoDTO;
import br.com.guilherme.gastos.exception.GanhoNaoEncontradoException;
import br.com.guilherme.gastos.repository.GanhoRepository;
import br.com.guilherme.gastos.utils.IterableToCollection;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.guilherme.gastos.specification.GanhoSpecification.*;

@Slf4j
@Service
@AllArgsConstructor
public class GanhoService {

    private GanhoRepository ganhoRepository;

    private IterableToCollection<Ganho> getIterableToCollection(){
        return new IterableToCollection<>();
    }

    @Transactional
    public Ganho inserirGanho(RequestInserirGanhoDTO requestInserirGanhoDTO){
        Ganho ganho = new Ganho();
        ganho.setDataEntrada(requestInserirGanhoDTO.getDataEntrada());
        ganho.setValor(requestInserirGanhoDTO.getValor());
        ganho.setDescricao(requestInserirGanhoDTO.getDescricao());

        return ganhoRepository.save(ganho);
    }

    public List<Ganho> consultarGanhoAnoMes(LocalDate localDate){
        Predicate predicate = byDataEntradaMes(localDate)
                        .and(byDataEntradaAno(localDate));

        return getIterableToCollection().toList(ganhoRepository.findAll(predicate));
    }

    public ResponseConsultarGanhoAnoMesDTO consultarGanhoAnoMes(Integer ano, Integer mes){
        LocalDate dataConsulta = LocalDate.of(ano, mes, 1);

        List<Ganho> ganhos = this.consultarGanhoAnoMes(dataConsulta);

        List<GanhoDTO> ganhoDTO = ganhos.stream().map(GanhoDTO::new).collect(Collectors.toList());

        return new ResponseConsultarGanhoAnoMesDTO(ganhoDTO);
    }

    public Ganho consultarGanho(Integer id){

        Optional<Ganho> ganhoOptional = ganhoRepository.findById(id);

        return ganhoOptional.orElseThrow(GanhoNaoEncontradoException::new);
    }

    @Transactional
    public Ganho alterarGanho(Integer id, RequestAlterarGanhoDTO requestAlterarGanhoDTO){

        Ganho ganho = consultarGanho(id);

        ganho.setValor(requestAlterarGanhoDTO.getValor());
        ganho.setDataEntrada(requestAlterarGanhoDTO.getDataEntrada());
        ganho.setDescricao(requestAlterarGanhoDTO.getDescricao());

        return ganhoRepository.save(ganho);
    }

    @Transactional
    public void deletarGanho(Integer id){

        Ganho ganho = consultarGanho(id);

        ganhoRepository.delete(ganho);
    }

}
