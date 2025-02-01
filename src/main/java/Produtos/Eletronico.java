package Produtos;

public class Eletronico extends Produto{
    int garantiaDias;

    public Eletronico(String nome, double preco, int garantia) {
        super(nome, preco);
        this.garantiaDias = garantia;
    }
}
