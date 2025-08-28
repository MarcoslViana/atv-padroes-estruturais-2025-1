package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Decorator que registra log detalhado das músicas tocadas
 */
public class LogMusicaDecorator extends TocadorMusicaDecorator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public LogMusicaDecorator(TocadorMusicaInterface tocador) {
        super(tocador);
    }

    @Override
    public void tocarMusica(MusicaInfo musica) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println("📝 [" + timestamp + "] Iniciando reprodução...");

        super.tocarMusica(musica);

        System.out.println("📝 [" + timestamp + "] Música: " + musica.getTitulo() +
                " | Artista: " + musica.getArtista() +
                " | Gênero: " + musica.getGenero() +
                " | Ano: " + musica.getAno());
    }
}
