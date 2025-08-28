package br.edu.ifpb.padroes.atv3.musicas.facade;

import br.edu.ifpb.padroes.atv3.musicas.abcd.ClienteHttpABCD;
import br.edu.ifpb.padroes.atv3.musicas.adapter.ServicoMusicaABCDAdapter;
import br.edu.ifpb.padroes.atv3.musicas.adapter.ServicoMusicaXPTOAdapter;
import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;
import br.edu.ifpb.padroes.atv3.musicas.comum.ServicoMusica;
import br.edu.ifpb.padroes.atv3.musicas.decorator.*;
import br.edu.ifpb.padroes.atv3.musicas.proxy.ServicoMusicaProxy;
import br.edu.ifpb.padroes.atv3.musicas.xpto.ClientHttpXPTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade que unifica o acesso ao sistema de streaming de música
 * Esconde a complexidade dos subsistemas e padrões implementados
 */
public class StreamMusicaFacade {

    private final List<ServicoMusica> servicos;
    private final TocadorMusicaInterface tocadorCompleto;
    private final ContadorMusicasDecorator contadorMusicas;
    private final EstatisticasArtistasDecorator estatisticasArtistas;
    private final EstatisticasGenerosDecorator estatisticasGeneros;

    public StreamMusicaFacade() {
        // Inicializa os serviços com adapters e proxies
        this.servicos = new ArrayList<>();

        // Serviço ABCD com cache
        ServicoMusica servicoABCD = new ServicoMusicaABCDAdapter(new ClienteHttpABCD());
        ServicoMusica servicoABCDComCache = new ServicoMusicaProxy(servicoABCD, 10); // 10 minutos de cache
        servicos.add(servicoABCDComCache);

        // Serviço XPTO com cache
        ServicoMusica servicoXPTO = new ServicoMusicaXPTOAdapter(new ClientHttpXPTO());
        ServicoMusica servicoXPTOComCache = new ServicoMusicaProxy(servicoXPTO, 10); // 10 minutos de cache
        servicos.add(servicoXPTOComCache);

        // Inicializa o tocador com todos os decorators
        TocadorMusicaInterface tocadorBasico = new TocadorMusicaBasico();
        LogMusicaDecorator logDecorator = new LogMusicaDecorator(tocadorBasico);
        this.contadorMusicas = new ContadorMusicasDecorator(logDecorator);
        this.estatisticasArtistas = new EstatisticasArtistasDecorator(contadorMusicas);
        this.estatisticasGeneros = new EstatisticasGenerosDecorator(estatisticasArtistas);
        this.tocadorCompleto = estatisticasGeneros;
    }

    /**
     * Lista todas as músicas de todos os serviços
     */
    public List<MusicaInfo> listarTodasMusicas() {
        System.out.println("🔍 Buscando músicas em todos os serviços...");
        return servicos.stream()
                .flatMap(servico -> servico.listarMusicas().stream())
                .collect(Collectors.toList());
    }

    /**
     * Busca músicas por título em todos os serviços
     */
    public List<MusicaInfo> buscarMusicasPorTitulo(String titulo) {
        System.out.println("🔍 Buscando músicas por título: " + titulo);
        return servicos.stream()
                .flatMap(servico -> servico.buscarPorTitulo(titulo).stream())
                .collect(Collectors.toList());
    }

    /**
     * Busca músicas por artista em todos os serviços
     */
    public List<MusicaInfo> buscarMusicasPorArtista(String artista) {
        System.out.println("🔍 Buscando músicas por artista: " + artista);
        return servicos.stream()
                .flatMap(servico -> servico.buscarPorArtista(artista).stream())
                .collect(Collectors.toList());
    }

    /**
     * Busca músicas por gênero em todos os serviços
     */
    public List<MusicaInfo> buscarMusicasPorGenero(String genero) {
        System.out.println("🔍 Buscando músicas por gênero: " + genero);
        return servicos.stream()
                .flatMap(servico -> servico.buscarPorGenero(genero).stream())
                .collect(Collectors.toList());
    }

    /**
     * Toca uma música com todas as funcionalidades (logging, estatísticas, etc.)
     */
    public void tocarMusica(MusicaInfo musica) {
        tocadorCompleto.tocarMusica(musica);
    }

    /**
     * Toca a primeira música encontrada pelo título
     */
    public void tocarMusicaPorTitulo(String titulo) {
        List<MusicaInfo> musicas = buscarMusicasPorTitulo(titulo);
        if (!musicas.isEmpty()) {
            tocarMusica(musicas.get(0));
        } else {
            System.out.println("❌ Música não encontrada: " + titulo);
        }
    }

    /**
     * Retorna estatísticas gerais do sistema
     */
    public String getEstatisticasGerais() {
        StringBuilder stats = new StringBuilder();
        stats.append("📊 === ESTATÍSTICAS DO SISTEMA ===\n");
        stats.append("Total de músicas tocadas: ").append(contadorMusicas.getContadorMusicas()).append("\n");
        stats.append("Artista mais tocado: ").append(estatisticasArtistas.getArtistaMaisTocado()).append("\n");
        stats.append("Gênero mais tocado: ").append(estatisticasGeneros.getGeneroMaisTocado()).append("\n");
        stats.append("Top 3 artistas: ").append(estatisticasArtistas.getTopArtistas(3)).append("\n");
        stats.append("Top 3 gêneros: ").append(estatisticasGeneros.getTopGeneros(3));
        return stats.toString();
    }

    /**
     * Reseta todas as estatísticas
     */
    public void resetarEstatisticas() {
        contadorMusicas.resetarContador();
        estatisticasArtistas.resetarEstatisticas();
        estatisticasGeneros.resetarEstatisticas();
        System.out.println("✅ Todas as estatísticas foram resetadas");
    }

    /**
     * Limpa o cache de todos os serviços
     */
    public void limparCache() {
        servicos.stream()
                .filter(servico -> servico instanceof ServicoMusicaProxy)
                .map(servico -> (ServicoMusicaProxy) servico)
                .forEach(ServicoMusicaProxy::limparCache);
        System.out.println("✅ Cache de todos os serviços foi limpo");
    }

    /**
     * Mostra informações sobre o cache
     */
    public void mostrarInfoCache() {
        System.out.println("📊 === INFORMAÇÕES DO CACHE ===");
        servicos.stream()
                .filter(servico -> servico instanceof ServicoMusicaProxy)
                .map(servico -> (ServicoMusicaProxy) servico)
                .forEach(proxy -> System.out.println(proxy.getEstatisticasCache()));
    }

    /**
     * Lista todos os serviços disponíveis
     */
    public void listarServicosDisponiveis() {
        System.out.println("🎵 === SERVIÇOS DISPONÍVEIS ===");
        System.out.println("1. Serviço ABCD (Música Brasileira) - localhost:3000");
        System.out.println("2. Serviço XPTO (Música Internacional) - localhost:4000");
        System.out.println("Total de serviços: " + servicos.size());
    }
}
