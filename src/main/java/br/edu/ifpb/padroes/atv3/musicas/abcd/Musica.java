package br.edu.ifpb.padroes.atv3.musicas.abcd;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

public record Musica(String id, String titulo, String artista, Long ano, String genero) implements MusicaInfo {

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getArtista() {
        return artista;
    }

    @Override
    public Long getAno() {
        return ano;
    }

    @Override
    public String getGenero() {
        return genero;
    }
}