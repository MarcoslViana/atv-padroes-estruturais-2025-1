package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;
import br.edu.ifpb.padroes.atv3.musicas.comum.ServicoMusica;
import br.edu.ifpb.padroes.atv3.musicas.xpto.ClientHttpXPTO;
import br.edu.ifpb.padroes.atv3.musicas.xpto.Song;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter para o serviço XPTO - adapta a interface específica do XPTO
 * para a interface comum ServicoMusica
 */
public class ServicoMusicaXPTOAdapter implements ServicoMusica {

    private final ClientHttpXPTO clienteXPTO;

    public ServicoMusicaXPTOAdapter(ClientHttpXPTO clienteXPTO) {
        this.clienteXPTO = clienteXPTO;
    }

    @Override
    public List<MusicaInfo> listarMusicas() {
        List<Song> songs = clienteXPTO.findAll();
        return songs.stream()
                .map(song -> (MusicaInfo) song)
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
