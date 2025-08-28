package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Decorator que registra estatísticas dos artistas mais tocados
 */
public class EstatisticasArtistasDecorator extends TocadorMusicaDecorator {

    private final Map<String, Integer> contadorArtistas = new HashMap<>();

    public EstatisticasArtistasDecorator(TocadorMusicaInterface tocador) {
        super(tocador);
    }

    @Override
    public void tocarMusica(MusicaInfo musica) {
        super.tocarMusica(musica);

        String artista = musica.getArtista();
        contadorArtistas.put(artista, contadorArtistas.getOrDefault(artista, 0) + 1);

        System.out.println("🎤 " + artista + " foi tocado " + contadorArtistas.get(artista) + " vez(es)");
    }

    public Map<String, Integer> getEstatisticasArtistas() {
        return new HashMap<>(contadorArtistas);
    }

    public String getArtistaMaisTocado() {
        return contadorArtistas.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum artista tocado ainda");
    }

    public String getTopArtistas(int limite) {
        return contadorArtistas.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limite)
                .map(entry -> entry.getKey() + " (" + entry.getValue() + " músicas)")
                .collect(Collectors.joining(", "));
    }

    public void resetarEstatisticas() {
        contadorArtistas.clear();
        System.out.println("🎤 Estatísticas de artistas resetadas");
    }
}
