package com.algaworks.algafoodclient.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
public class ClientApiException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    @Getter
    private Problem problem;

    public ClientApiException(String message, RestClientResponseException cause) {
        super(message, cause);
        deserializeProblem(cause);
    }

    private void deserializeProblem(RestClientResponseException cause) {

        /*  Classe do Jackson que serializa e deserializa JSON em Object */
        ObjectMapper mapper = new ObjectMapper();

        /* Isso serve para não falhar caso o mapper encontre propriedades no retorno que
        * não existam no Problem, como por exemplo o tipo que não está mapeado */
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        /* Isso serve para que o ObjectMapper consiga deserializar o OffsetDateTime do java */
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();

        String problemString = cause.getResponseBodyAsString();

        try {
            this.problem = mapper.readValue(problemString, Problem.class);
        } catch (JsonProcessingException e) {
            log.warn("Não foi possível desserializar a resposta em um problema", e);
        }

    }

}
