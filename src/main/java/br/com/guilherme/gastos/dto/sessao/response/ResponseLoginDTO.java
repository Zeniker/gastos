package br.com.guilherme.gastos.dto.sessao.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseLoginDTO {

    private String token;
}
