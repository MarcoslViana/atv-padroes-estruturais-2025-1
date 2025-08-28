package br.edu.ifpb.padroes.atv3.musicas.xpto;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;

public record Song(String id, String title, String artist, Long year, String genre) implements MusicaInfo {

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitulo() {
        return title;
    }

    @Override
    public String getArtista() {
        return artist;
    }

    @Override
    public Long getAno() {
        return year;
    }

    @Override
    public String getGenero() {
        return genre;
    }
}