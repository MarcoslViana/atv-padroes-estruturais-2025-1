package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

/**
 * Interface para tocador de música que permite decoração
 */
public interface TocadorMusicaInterface {
    void tocarMusica(MusicaInfo musica);
}