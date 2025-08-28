package br.edu.ifpb.padroes.atv3.musicas.facade.interfaces;

/**
 * Interface segregada para controle de cache
 * Segue o princípio ISP - Interface Segregation Principle
 */
public interface ControladorCache {
    void limparCache();
    void mostrarInfoCache();
}