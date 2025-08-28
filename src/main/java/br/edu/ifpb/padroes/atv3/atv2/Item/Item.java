package br.edu.ifpb.padroes.atv3.atv2.Item;

public abstract class Item {

    protected String nome;
    protected double preco;
    protected String descricao;

    public Item(String nome, double preco, String descricao){
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public abstract String getNome();

    public abstract double getPreco();

    public abstract String getDescricao();

    public abstract void exibir();

}
