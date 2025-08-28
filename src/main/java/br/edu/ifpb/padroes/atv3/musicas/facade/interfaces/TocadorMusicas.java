package br.edu.ifpb.padroes.atv3.musicas.facade.interfaces;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

/**
 * Interface segregada para reprodução de músicas
 * Segue o princípio ISP - Interface Segregation Principle
 */
public interface TocadorMusicas {
    void tocarMusica(MusicaInfo musica);
    void tocarMusicaPorTitulo(String titulo);
}