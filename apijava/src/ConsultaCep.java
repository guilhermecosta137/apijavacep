import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCep {

    public Endereco buscaEndereco(String cep) {
        // Validação básica do CEP
        String cepLimpo = cep.replaceAll("[^0-9]", "");
        if (cepLimpo.length() != 8) {
            throw new RuntimeException("CEP inválido. Digite um CEP com 8 dígitos.");
        }

        URI endereco = URI.create("https://viacep.com.br/ws/" + cepLimpo + "/json/");

        HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica se a resposta foi bem-sucedida
            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro ao consultar a API. Código: " + response.statusCode());
            }

            Endereco enderecoObj = new Gson().fromJson(response.body(), Endereco.class);
            
            // A API do ViaCEP retorna um objeto com "erro": true quando o CEP não existe
            if (enderecoObj.cep() == null) {
                throw new RuntimeException("CEP não encontrado.");
            }

            return enderecoObj;
        } catch (IOException e) {
            throw new RuntimeException("Erro de conexão ao consultar o CEP: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Consulta interrompida: " + e.getMessage());
        }
    }
}