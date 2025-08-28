package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.abcd.ClienteHttpABCD;
import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;
import br.edu.ifpb.padroes.atv3.musicas.comum.ServicoMusica;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter para o serviço ABCD - adapta a interface específica do ABCD
 * para a interface comum ServicoMusica
 */
public class ServicoMusicaABCDAdapter implements ServicoMusica {

    private final ClienteHttpABCD clienteABCD;

    public ServicoMusicaABCDAdapter(ClienteHttpABCD clienteABCD) {
        this.clienteABCD = clienteABCD;
    }

    @Override
    public List<MusicaInfo> listarMusicas() {
        List<Musica> musicas = clienteABCD.listarMusicas();
        return musicas.stream()
                .map(musica -> (MusicaInfo) musica)
                .collect(Collectors.toList());
    }

    @Override
    public List<MusicaInfo> buscarPorTitulo(String titulo) {
        return listarMusicas().stream()
                .filter(musica -> musica.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MusicaInfo> buscarPorArtista(String artista) {
        return listarMusicas().stream()
                .filter(musica -> musica.getArtista().toLowerCase().contains(artista.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MusicaInfo> buscarPorGenero(String genero) {
        return listarMusicas().stream()
                .filter(musica -> musica.getGenero().toLowerCase().contains(genero.toLowerCase()))
                .collect(Collectors.toList());
    }
}
