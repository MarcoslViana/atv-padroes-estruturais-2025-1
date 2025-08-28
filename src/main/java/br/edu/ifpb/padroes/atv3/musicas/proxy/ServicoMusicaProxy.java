package br.edu.ifpb.padroes.atv3.musicas.proxy;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;
import br.edu.ifpb.padroes.atv3.musicas.comum.ServicoMusica;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Proxy para cache de músicas - evita solicitar dados do backend a cada busca
 * Implementa cache com tempo de vida configurável
 */
public class ServicoMusicaProxy implements ServicoMusica {

    private final ServicoMusica servicoReal;
    private final Map<String, CacheEntry> cache;
    private final long tempoVidaCacheMinutos;

    public ServicoMusicaProxy(ServicoMusica servicoReal, long tempoVidaCacheMinutos) {
        this.servicoReal = servicoReal;
        this.cache = new HashMap<>();
        this.tempoVidaCacheMinutos = tempoVidaCacheMinutos;
    }

    public ServicoMusicaProxy(ServicoMusica servicoReal) {
        this(servicoReal, 5); // 5 minutos de cache por padrão
    }

    @Override
    public List<MusicaInfo> listarMusicas() {
        String chaveCache = "listar_todas";
        return obterComCache(chaveCache, () -> servicoReal.listarMusicas());
    }

    @Override
    public List<MusicaInfo> buscarPorTitulo(String titulo) {
        String chaveCache = "titulo_" + titulo.toLowerCase();
        return obterComCache(chaveCache, () -> servicoReal.buscarPorTitulo(titulo));
    }

    @Override
    public List<MusicaInfo> buscarPorArtista(String artista) {
        String chaveCache = "artista_" + artista.toLowerCase();
        return obterComCache(chaveCache, () -> servicoReal.buscarPorArtista(artista));
    }

    @Override
    public List<MusicaInfo> buscarPorGenero(String genero) {
        String chaveCache = "genero_" + genero.toLowerCase();
        return obterComCache(chaveCache, () -> servicoReal.buscarPorGenero(genero));
    }

    private List<MusicaInfo> obterComCache(String chave, OperacaoBusca operacao) {
        CacheEntry entrada = cache.get(chave);

        // Verifica se o cache existe e não expirou
        if (entrada != null && !cacheExpirou(entrada.getTimestamp())) {
            System.out.println("Cache HIT para: " + chave);
            return entrada.getMusicas();
        }

        // Cache miss ou expirado - busca dados reais
        System.out.println("Cache MISS para: " + chave + " - buscando dados...");
        List<MusicaInfo> musicas = operacao.executar();
        cache.put(chave, new CacheEntry(musicas, LocalDateTime.now()));

        return musicas;
    }

    private boolean cacheExpirou(LocalDateTime timestamp) {
        return ChronoUnit.MINUTES.between(timestamp, LocalDateTime.now()) > tempoVidaCacheMinutos;
    }

    /**
     * Limpa todo o cache
     */
    public void limparCache() {
        cache.clear();
        System.out.println("Cache limpo");
    }

    /**
     * Retorna estatísticas do cache
     */
    public String getEstatisticasCache() {
        long entradasValidas = cache.values().stream()
                .mapToLong(entrada -> cacheExpirou(entrada.getTimestamp()) ? 0 : 1)
                .sum();

        return String.format("Cache: %d entradas totais, %d válidas", cache.size(), entradasValidas);
    }

    @FunctionalInterface
    private interface OperacaoBusca {
        List<MusicaInfo> executar();
    }

    private static class CacheEntry {
        private final List<MusicaInfo> musicas;
        private final LocalDateTime timestamp;

        public CacheEntry(List<MusicaInfo> musicas, LocalDateTime timestamp) {
            this.musicas = musicas;
            this.timestamp = timestamp;
        }

        public List<MusicaInfo> getMusicas() {
            return musicas;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
