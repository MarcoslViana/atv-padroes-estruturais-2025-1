package br.edu.ifpb.padroes.atv3.musicas.abcd;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ClienteHttpABCD {

    public static final String URI_SERVICO = "http://localhost:3000/musicas";

    public List<Musica> listarMusicas() {
        try {
            HttpRequest musicaRequest = HttpRequest.newBuilder(new URI(URI_SERVICO)).GET().build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(musicaRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            List<Musica> musicas = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, Musica.class));
            return musicas;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}