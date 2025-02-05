package Produtos;

public class Alimento extends Produto {
    public int validadeDias;

    public Alimento(String nome, double preco, int validade) {
        super(nome, preco);
        this.validadeDias = validade;
    }
}
