package br.edu.ifpb.padroes.atv3.atv2;

import br.edu.ifpb.padroes.atv3.atv2.Bebida.Bebida;
import br.edu.ifpb.padroes.atv3.atv2.Comida.Comida;
import br.edu.ifpb.padroes.atv3.atv2.Sobremesa.Sobremesa;
import br.edu.ifpb.padroes.atv3.atv2.Cardapio.Cardapio;
import br.edu.ifpb.padroes.atv3.atv2.Combo.Combo;
import br.edu.ifpb.padroes.atv3.atv2.Item.Item;

public class Main {

    public static void main(String[] args) {
        //Criando itens mais simples, comida, bebida e sobremesas
        Item Lasanha = new Comida("Lasanha", 42.0,"Lasanha à bolonhesa");
        Item Suco = new Bebida("Suco", 9.0,"Suco de Laranja");
        Item Mousse = new Sobremesa("Mousse", 14.0,"Mousse de Maracujá");

        System.out.println("*******************************************");

        //Criando combos juntando prato, bebidas, sobremesas
        Combo comboJantar = new Combo("Jantar Moda da Casa", 6.0,"Combo promocional da noite",0);
        comboJantar.adicionar(Lasanha);
        comboJantar.adicionar(Suco);
        comboJantar.adicionar(Mousse);


        //Criando combos juntando prato, bebidas, sobremesas e também outros combos
        Combo megaCombo = new Combo("Mega Combo Família", 12.0,"Opção completa para dividir",0);
        megaCombo.adicionar(comboJantar);
        megaCombo.adicionar(new Comida("Batata Frita", 15.0,"Batata frita com bacon"));


        //Criando e adicionando itens ao cardápio do restaurante
        Cardapio cardapio = new Cardapio();
        cardapio.adicionar(Lasanha);
        cardapio.adicionar(Suco);
        cardapio.adicionar(comboJantar);
        cardapio.adicionar(megaCombo);


        // Exibindo todos os itens do cardápio do restaurante
        cardapio.exibir();
    }
}