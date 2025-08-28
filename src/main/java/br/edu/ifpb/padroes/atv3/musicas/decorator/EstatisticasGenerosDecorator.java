package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Decorator que registra estatísticas dos gêneros mais tocados
 */
public class EstatisticasGenerosDecorator extends TocadorMusicaDecorator {

    private final Map<String, Integer> contadorGeneros = new HashMap<>();

    public EstatisticasGenerosDecorator(TocadorMusicaInterface tocador) {
        super(tocador);
    }

    @Override
    public void tocarMusica(MusicaInfo musica) {
        super.tocarMusica(musica);

        String genero = musica.getGenero();
        contadorGeneros.put(genero, contadorGeneros.getOrDefault(genero, 0) + 1);

        System.out.println("🎵 Gênero " + genero + " foi tocado " + contadorGeneros.get(genero) + " vez(es)");
    }

    public Map<String, Integer> getEstatisticasGeneros() {
        return new HashMap<>(contadorGeneros);
    }

    public String getGeneroMaisTocado() {
        return contadorGeneros.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum gênero tocado ainda");
    }

    public String getTopGeneros(int limite) {
        return contadorGeneros.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limite)
                .map(entry -> entry.getKey() + " (" + entry.getValue() + " músicas)")
                .collect(Collectors.joining(", "));
    }

    public void resetarEstatisticas() {
        contadorGeneros.clear();
        System.out.println("🎵 Estatísticas de gêneros resetadas");
    }
}
