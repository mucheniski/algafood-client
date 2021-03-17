package com.algaworks.algafoodclient.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteResumoDTO {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;

}
