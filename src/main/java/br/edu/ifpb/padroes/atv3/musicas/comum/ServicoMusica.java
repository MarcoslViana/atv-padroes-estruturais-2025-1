package br.edu.ifpb.padroes.atv3.musicas.comum;

import java.util.List;

/**
 * Interface comum para serviços de música
 */
public interface ServicoMusica {
    List<MusicaInfo> listarMusicas();
    List<MusicaInfo> buscarPorTitulo(String titulo);
    List<MusicaInfo> buscarPorArtista(String artista);
    List<MusicaInfo> buscarPorGenero(String genero);
}
