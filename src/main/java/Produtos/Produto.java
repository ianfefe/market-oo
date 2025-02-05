package Produtos;

public class Produto {
    public String nome;
    private static int id = 0;
    private final int idUnico;
    private double preco;

    protected Produto(String nome, double preco) {
        this.idUnico = id++;
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return this.nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double valor) {
        if (valor > 0)
            this.preco = valor;
        else
            System.out.println("Valor não permitido");
    }
}
