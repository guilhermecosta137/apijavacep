package com.example.cepconsultation.service;

import com.example.cepconsultation.model.Endereco;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsultaCepService {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaCepService.class);

    public Endereco buscaEndereco(String cep) {
        logger.info("Consultando CEP: {}", cep);
        URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json/");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("Erro na resposta da API: {}", response.statusCode());
                throw new RuntimeException("CEP não encontrado ou inválido.");
            }

            Endereco enderecoObj = new Gson().fromJson(response.body(), Endereco.class);
            if (enderecoObj.cep() == null) {
                throw new RuntimeException("CEP não encontrado.");
            }

            logger.info("Endereço encontrado: {}", enderecoObj);
            return enderecoObj;
        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao consultar CEP: {}", e.getMessage());
            throw new RuntimeException("Erro ao consultar CEP: " + e.getMessage());
        }
    }
}
