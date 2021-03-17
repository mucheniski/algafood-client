package com.algaworks.algafoodclient;

import com.algaworks.algafoodclient.api.RestauranteClient;
import com.algaworks.algafoodclient.dto.*;
import com.algaworks.algafoodclient.exception.ClientApiException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class RestaurantesMain {

    private static final String URL = "http://localhost:8080";

    public static void main(String[] args) {

        // listarRestaurantes();
        post();

    }

    private static void post() {

        var cozinha =
                CozinhaIdDTO.builder()
                    .id(1L)
                .build();

        var cidade =
                CidadeIdDTO.builder()
                        .id(1L)
                .build();

        var endereco =
                EnderecoPostDTO.builder()
                        .cep("86000-000")
                        .logradouro("Logradouro API Client")
                        .numero("1")
                        .complemento("Post feito no Client")
                        .bairro("Centro")
                        .cidade(cidade)
                .build();

        var restaurante =
                RestaurantePostDTO.builder()
                            .nome("Restaurante API Client")
                            .taxaFrete(new BigDecimal(10.0))
                            .cozinha(cozinha)
                            .endereco(endereco)
                        .build();

        System.out.println("Cozinha: " + cozinha);
        System.out.println("Cidade: " + cidade);
        System.out.println("Endereço: " + endereco);
        System.out.println("Restaurante: " + restaurante);

        RestTemplate restTemplate = new RestTemplate();
        RestauranteClient restauranteClient = new RestauranteClient(restTemplate, URL);
        var restauranteRetornoDTO = restauranteClient.salvar(restaurante);
        System.out.println("RESTAURANTE RETORNO: " + restauranteRetornoDTO);

    }

    private static void listarRestaurantes() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            RestauranteClient restauranteClient = new RestauranteClient(restTemplate, URL);
            restauranteClient.listar().stream().forEach(restaurante -> System.out.print(restaurante));
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println();
                System.out.println("PROBLEMA COMPLETO");
                System.out.println(e.getProblem());
                System.out.println("========================================");
                System.out.println("MENSAGEM PARA O USUÁRIO");
                System.out.println(e.getProblem().getMensagemParaUsuario());
                System.out.println();
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }

}
