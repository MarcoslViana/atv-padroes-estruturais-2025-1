package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

/**
 * Decorator que conta quantas músicas foram tocadas
 */
public class ContadorMusicasDecorator extends TocadorMusicaDecorator {

    private int contadorMusicas = 0;

    public ContadorMusicasDecorator(TocadorMusicaInterface tocador) {
        super(tocador);
    }

    @Override
    public void tocarMusica(MusicaInfo musica) {
        super.tocarMusica(musica);
        contadorMusicas++;
        System.out.println("📊 Total de músicas tocadas: " + contadorMusicas);
    }

    public int getContadorMusicas() {
        return contadorMusicas;
    }

    public void resetarContador() {
        contadorMusicas = 0;
        System.out.println("📊 Contador de músicas resetado");
    }
}
