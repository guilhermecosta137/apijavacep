import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivo {

    public void salvaJson(Endereco endereco) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        
        // Try-with-resources garante que o arquivo seja fechado automaticamente
        try (FileWriter escrita = new FileWriter(endereco.cep() + ".json")) {
            escrita.write(gson.toJson(endereco));
        }
    }
}