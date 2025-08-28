package br.edu.ifpb.padroes.atv3.atv2.Combo;

import br.edu.ifpb.padroes.atv3.atv2.Item.Item;

import java.util.ArrayList;
import java.util.List;

public class Combo extends Item {
    private List<Item> itensCombo = new ArrayList<>();
    private double descontoCombo;
    public Combo(String nome, double preco, String descricao,double descontoCombo) {
        super(nome, preco, descricao);
        this.descontoCombo = descontoCombo;
    }

    public void adicionar(Item item) {
        itensCombo.add(item);
    }

    @Override
    public String getNome() {

        return "Combo: " + nome;
    }

    @Override
    public double getPreco() {
        double total = 0;
        for(Item itemCombo : itensCombo){
            total += itemCombo.getPreco();
        }
        return total - (total * descontoCombo/100);
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void exibir() {
        System.out.println("+ Combo: " + getDescricao() + " (R$ " + getPreco() + ")");
        System.out.println("Itens do combo");
        for (Item item : itensCombo) {
            item.exibir();
        }
    }
}
