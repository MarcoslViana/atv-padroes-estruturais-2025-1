package br.edu.ifpb.padroes.atv3.musicas;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaInfo;
import br.edu.ifpb.padroes.atv3.musicas.facade.StreamMusicaFacade;

import java.util.List;
import java.util.Scanner;

/**
 * Aplicação principal que demonstra todos os padrões estruturais implementados:
 * - Adapter: Unifica interfaces dos serviços ABCD e XPTO
 * - Proxy: Implementa cache para evitar requisições desnecessárias
 * - Decorator: Adiciona funcionalidades ao tocador (log, estatísticas)
 * - Facade: Fornece interface única e simplificada para todo o sistema
 */
public class Main {

    private static final StreamMusicaFacade streamFacade = new StreamMusicaFacade();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🎵 === SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("Demonstração dos Padrões Estruturais");
        System.out.println("=====================================\n");

        // Demonstração inicial
        demonstrarSistema();

        // Menu interativo
        menuInterativo();
    }

    private static void demonstrarSistema() {
        System.out.println("🔧 DEMONSTRANDO OS PADRÕES IMPLEMENTADOS:\n");

        // 1. Facade - Interface única
        System.out.println("1️⃣ FACADE PATTERN - Interface única para todo o sistema");
        streamFacade.listarServicosDisponiveis();
        System.out.println();

        // 2. Adapter + Proxy - Buscar músicas (com cache)
        System.out.println("2️⃣ ADAPTER + PROXY PATTERNS - Unificação de serviços com cache");
        List<MusicaInfo> musicasBrasileiras = streamFacade.buscarMusicasPorGenero("MPB");
        System.out.println("Encontradas " + musicasBrasileiras.size() + " músicas de MPB");

        // Segunda busca para demonstrar cache
        System.out.println("\n🔄 Fazendo a mesma busca novamente (deve usar cache):");
        musicasBrasileiras = streamFacade.buscarMusicasPorGenero("MPB");
        System.out.println();

        // 3. Decorator - Tocar músicas com funcionalidades extras
        System.out.println("3️⃣ DECORATOR PATTERN - Funcionalidades extensíveis do player");
        if (!musicasBrasileiras.isEmpty()) {
            streamFacade.tocarMusica(musicasBrasileiras.get(0));
        }

        // Buscar e tocar música internacional
        List<MusicaInfo> musicasRock = streamFacade.buscarMusicasPorGenero("Rock");
        if (!musicasRock.isEmpty()) {
            streamFacade.tocarMusica(musicasRock.get(0));
        }

        System.out.println();

        // 4. Mostrar estatísticas coletadas pelos decorators
        System.out.println("4️⃣ ESTATÍSTICAS COLETADAS PELOS DECORATORS:");
        System.out.println(streamFacade.getEstatisticasGerais());
        System.out.println();

        // 5. Informações do cache (Proxy)
        streamFacade.mostrarInfoCache();
        System.out.println();
    }

    private static void menuInterativo() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> listarTodasMusicas();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorArtista();
                case 4 -> buscarPorGenero();
                case 5 -> tocarMusicaPorTitulo();
                case 6 -> mostrarEstatisticas();
                case 7 -> resetarEstatisticas();
                case 8 -> limparCache();
                case 9 -> streamFacade.mostrarInfoCache();
                case 0 -> {
                    continuar = false;
                    System.out.println("👋 Obrigado por usar o Sistema de Streaming!");
                }
                default -> System.out.println("❌ Opção inválida!");
            }

            if (continuar) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n🎵 === MENU PRINCIPAL ===");
        System.out.println("1. Listar todas as músicas");
        System.out.println("2. Buscar por título");
        System.out.println("3. Buscar por artista");
        System.out.println("4. Buscar por gênero");
        System.out.println("5. Tocar música por título");
        System.out.println("6. Mostrar estatísticas");
        System.out.println("7. Resetar estatísticas");
        System.out.println("8. Limpar cache");
        System.out.println("9. Informações do cache");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void listarTodasMusicas() {
        System.out.println("\n📋 LISTANDO TODAS AS MÚSICAS:");
        List<MusicaInfo> musicas = streamFacade.listarTodasMusicas();

        if (musicas.isEmpty()) {
            System.out.println("❌ Nenhuma música encontrada. Verifique se os serviços estão rodando!");
            return;
        }

        musicas.forEach(musica ->
                System.out.println("♪ " + musica.getTitulo() + " - " + musica.getArtista() +
                        " (" + musica.getGenero() + ", " + musica.getAno() + ")")
        );
        System.out.println("\nTotal: " + musicas.size() + " músicas");
    }

    private static void buscarPorTitulo() {
        System.out.print("\n🔍 Digite o título da música: ");
        String titulo = scanner.nextLine();

        List<MusicaInfo> musicas = streamFacade.buscarMusicasPorTitulo(titulo);
        mostrarResultados(musicas, "título '" + titulo + "'");
    }

    private static void buscarPorArtista() {
        System.out.print("\n🔍 Digite o nome do artista: ");
        String artista = scanner.nextLine();

        List<MusicaInfo> musicas = streamFacade.buscarMusicasPorArtista(artista);
        mostrarResultados(musicas, "artista '" + artista + "'");
    }

    private static void buscarPorGenero() {
        System.out.print("\n🔍 Digite o gênero: ");
        String genero = scanner.nextLine();

        List<MusicaInfo> musicas = streamFacade.buscarMusicasPorGenero(genero);
        mostrarResultados(musicas, "gênero '" + genero + "'");
    }

    private static void tocarMusicaPorTitulo() {
        System.out.print("\n🎵 Digite o título da música para tocar: ");
        String titulo = scanner.nextLine();

        streamFacade.tocarMusicaPorTitulo(titulo);
    }

    private static void mostrarEstatisticas() {
        System.out.println("\n" + streamFacade.getEstatisticasGerais());
    }

    private static void resetarEstatisticas() {
        streamFacade.resetarEstatisticas();
    }

    private static void limparCache() {
        streamFacade.limparCache();
    }

    private static void mostrarResultados(List<MusicaInfo> musicas, String criterio) {
        if (musicas.isEmpty()) {
            System.out.println("❌ Nenhuma música encontrada para " + criterio);
            return;
        }

        System.out.println("\n📋 Músicas encontradas para " + criterio + ":");
        musicas.forEach(musica ->
                System.out.println("♪ " + musica.getTitulo() + " - " + musica.getArtista() +
                        " (" + musica.getGenero() + ", " + musica.getAno() + ")")
        );
        System.out.println("Total: " + musicas.size() + " músicas");
    }
}
