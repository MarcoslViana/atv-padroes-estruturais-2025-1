package br.edu.ifpb.padroes.atv3.musicas.facade.interfaces;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;
import java.util.List;

/**
 * Interface segregada para operações de busca de música
 * Segue o princípio ISP - Interface Segregation Principle
 */
public interface BuscadorMusica {
    List<MusicaInfo> listarTodasMusicas();
    List<MusicaInfo> buscarPorTitulo(String titulo);
    List<MusicaInfo> buscarPorArtista(String artista);
    List<MusicaInfo> buscarPorGenero(String genero);
}
