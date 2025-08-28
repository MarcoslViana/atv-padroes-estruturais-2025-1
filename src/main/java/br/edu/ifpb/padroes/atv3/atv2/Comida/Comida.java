package br.edu.ifpb.padroes.atv3.atv2.Comida;

import br.edu.ifpb.padroes.atv3.atv2.Item.Item;

public class Comida extends Item {


    public Comida(String nome, double preco, String descricao) {
        super(nome, preco, descricao);
    }

    @Override
    public String getNome() {
        return "- Comida: " + nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void exibir() {
        System.out.println(getNome() + " - R$" + getPreco());
        System.out.println("Descrição: " + getDescricao());

    }
}