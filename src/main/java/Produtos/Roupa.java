package Produtos;

public class Roupa extends Produto{
    TamanhosRoupa tamanho;
    CoresRoupa cor;

    public Roupa(String nome, double preco, TamanhosRoupa tamanho, CoresRoupa cor) {
        super(nome, preco);
        this.tamanho = tamanho;
        this.cor = cor;
    }
}