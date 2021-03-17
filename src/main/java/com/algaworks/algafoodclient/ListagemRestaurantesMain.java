package com.algaworks.algafoodclient;

import com.algaworks.algafoodclient.api.RestauranteClient;
import com.algaworks.algafoodclient.exception.ClientApiException;
import org.springframework.web.client.RestTemplate;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class ListagemRestaurantesMain {

    private static final String URL = "http://localhost:8080";

    public static void main(String[] args) {

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
