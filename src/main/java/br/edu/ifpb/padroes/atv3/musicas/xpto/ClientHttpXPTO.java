package br.edu.ifpb.padroes.atv3.musicas.xpto;

import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ClientHttpXPTO {

    public static final String SERVICE_URI = "http://localhost:4000/musics";

    public List<Song> findAll() {
        try {
            HttpRequest songsRequest = HttpRequest.newBuilder(new URI(SERVICE_URI)).GET().build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(songsRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            List<Song> songsRetrieved = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, Song.class));
            return songsRetrieved;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}