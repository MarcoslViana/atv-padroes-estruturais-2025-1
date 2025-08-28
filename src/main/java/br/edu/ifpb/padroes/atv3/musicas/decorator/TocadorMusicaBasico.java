package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;
import br.edu.ifpb.padroes.atv3.musicas.servico.MusicaNaoEncontradaException;

/**
 * Implementação básica do tocador de música
 */
public class TocadorMusicaBasico implements TocadorMusicaInterface {

    @Override
    public void tocarMusica(MusicaInfo musica) {
        if (musica == null) {
            throw new MusicaNaoEncontradaException();
        }

        System.out.println("♪ Tocando música: " + musica.getTitulo() + " - " + musica.getArtista());
    }
}
