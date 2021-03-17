package com.algaworks.algafoodclient.exception;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Problem {

    // Os nomes dos atributos precisaram ser em Português porque na API foram definidos assim
    private Integer status;
    private OffsetDateTime dataHoraAtual;
    private String mensagemParaUsuario;

}
