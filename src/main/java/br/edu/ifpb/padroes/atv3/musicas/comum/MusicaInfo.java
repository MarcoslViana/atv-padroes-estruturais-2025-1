package br.edu.ifpb.padroes.atv3.musicas.comum;

/**
 * Interface comum para representar informações de música
 * independente da fonte de dados (ABCD ou XPTO)
 */
public interface MusicaInfo {
    String getId();
    String getTitulo();
    String getArtista();
    Long getAno();
    String getGenero();
}
