package br.edu.ifpb.padroes.atv3.atv2.Bebida;

import br.edu.ifpb.padroes.atv3.atv2.Item.Item;

public class Bebida extends Item {

    public Bebida(String nome, double preco, String descricao) {
        super(nome, preco, descricao);
    }

    @Override
    public String getNome() {
        return "- Bebida: " + nome;
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
