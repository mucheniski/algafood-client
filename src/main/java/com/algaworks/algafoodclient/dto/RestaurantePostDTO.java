package com.algaworks.algafoodclient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@Builder
public class RestaurantePostDTO {

    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaIdDTO cozinha;
    private EnderecoPostDTO endereco;

}
