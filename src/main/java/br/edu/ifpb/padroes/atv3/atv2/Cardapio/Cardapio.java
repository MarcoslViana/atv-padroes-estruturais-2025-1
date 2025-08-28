package br.edu.ifpb.padroes.atv3.atv2.Cardapio;

import br.edu.ifpb.padroes.atv3.atv2.Item.Item;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {

    private List<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        itens.add(item);
    }

    public void exibir() {
        System.out.println("********* CARDÁPIO DO RESTAURANTE *********");
        System.out.println("\n");
        for (Item item : itens) {
            item.exibir();
            System.out.println(" ");
        }
    }
}