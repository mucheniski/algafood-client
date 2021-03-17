package com.algaworks.algafoodclient.api;

import com.algaworks.algafoodclient.dto.RestauranteResumoDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/*
    Inserido o AllArgsConstructor para que quem for usar a classe RestauranteCliente
    tenha que passar os parametros que ela utiliza
 */
@AllArgsConstructor
public class RestauranteClient {

    private static final String RESOURCE_PATH = "/restaurante";

    /* Classe do próprio Spring que auxilia nas requisições HTTP */
    RestTemplate restTemplate;

    private String url;

    public List<RestauranteResumoDTO> listar() {

        URI resourceURI = URI.create(url + RESOURCE_PATH);

        RestauranteResumoDTO[] restaurantes = restTemplate.getForObject(resourceURI, RestauranteResumoDTO[].class);

        return Arrays.asList(restaurantes);

    }

}
