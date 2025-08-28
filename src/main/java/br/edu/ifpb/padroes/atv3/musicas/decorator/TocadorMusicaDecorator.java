package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

/**
 * Decorator base para funcionalidades do tocador de música
 */
public abstract class TocadorMusicaDecorator implements TocadorMusicaInterface {

    protected final TocadorMusicaInterface tocador;

    public TocadorMusicaDecorator(TocadorMusicaInterface tocador) {
        this.tocador = tocador;
    }

    @Override
    public void tocarMusica(MusicaInfo musica) {
        tocador.tocarMusica(musica);
    }
}
