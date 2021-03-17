package com.algaworks.algafoodclient;

import com.algaworks.algafoodclient.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {

    private static final String URL = "http://localhost:8080";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        RestauranteClient restauranteClient = new RestauranteClient(restTemplate, URL);
        restauranteClient.listar().stream().forEach(restaurante -> System.out.print(restaurante));

    }

}
