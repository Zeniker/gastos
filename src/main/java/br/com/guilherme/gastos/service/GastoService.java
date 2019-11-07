package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.dto.gasto.GastoDTO;
import br.com.guilherme.gastos.dto.gasto.request.RequestAlterarGastoDTO;
import br.com.guilherme.gastos.dto.gasto.request.RequestInserirGastoDTO;
import br.com.guilherme.gastos.dto.gasto.response.ResponseConsultarGastoAnoMesDTO;
import br.com.guilherme.gastos.exception.GastoNaoEncontradoException;
import br.com.guilherme.gastos.repository.GastoRepository;
import br.com.guilherme.gastos.utils.IterableToCollection;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.guilherme.gastos.specification.GastoSpecification.*;

@Service
@AllArgsConstructor
public class GastoService {

    private GastoRepository gastoRepository;

    private IterableToCollection<Ganho> getIterableToCollection(){
        return new IterableToCollection<>();
    }

    public Gasto inserirGasto(RequestInserirGastoDTO requestInserirGastoDTO){

        Gasto gasto = new Gasto();
        gasto.setDataEntrada(requestInserirGastoDTO.getDataEntrada());
        gasto.setValor(requestInserirGastoDTO.getValor());
        gasto.setDescricao(requestInserirGastoDTO.getDescricao());

        return gastoRepository.save(gasto);
    }

    public List<Gasto> consultarGastoAnoMes(LocalDate localDate){

        Predicate predicate = byDataEntradaMes(localDate)
                        .and(byDataEntradaAno(localDate));

        return getIterableToCollection().toList(gastoRepository.findAll(predicate));
    }

    public ResponseConsultarGastoAnoMesDTO consultarGastoAnoMes(Integer ano, Integer mes){

        LocalDate dataConsulta = LocalDate.of(ano, mes, 1);

        List<Gasto> gastos = this.consultarGastoAnoMes(dataConsulta);

        List<GastoDTO> ganhoDTO = gastos.stream().map(GastoDTO::new).collect(Collectors.toList());

        return new ResponseConsultarGastoAnoMesDTO(ganhoDTO);
    }

    public Gasto buscarGasto(Integer id){

        Optional<Gasto> gasto = gastoRepository.findById(id);

        return gasto.orElseThrow(GastoNaoEncontradoException::new);
    }

    @Transactional
    public Gasto alterarGasto(Integer id, RequestAlterarGastoDTO requestAlterarGastoDTO){

        Gasto gasto = buscarGasto(id);

        gasto.setValor(requestAlterarGastoDTO.getValor());
        gasto.setDataEntrada(requestAlterarGastoDTO.getDataEntrada());
        gasto.setDescricao(requestAlterarGastoDTO.getDescricao());

        return gastoRepository.save(gasto);
    }

    @Transactional
    public void deletarGasto(Integer id){

        Gasto gasto = buscarGasto(id);

        gastoRepository.delete(gasto);
    }

}
