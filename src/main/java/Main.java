import Cupons.CupomQuantidadeLimitada;
import Cupons.CupomValorMinimo;
import Produtos.Alimento;
import Produtos.Eletronico;
import Produtos.Produto;
import Vendas.GestorVendas;
import Vendas.Venda;

public class Main {
    public static void main(String[] args) {
        Eletronico celular = new Eletronico("Celular",1200, 90);
        Alimento batata = new Alimento("Cenoura", 3, 7);

        Venda venda1 = new Venda();
        venda1.adicionarProduto(batata);
        venda1.adicionarProduto(celular);

        Venda venda2 = new Venda();
        venda2.adicionarProduto(celular);
        venda2.adicionarProduto(celular);
        venda2.adicionarProduto(celular);

        GestorVendas ian = new GestorVendas();

        CupomQuantidadeLimitada cupom100 = new CupomQuantidadeLimitada("CUPOM10",10,1);
        CupomValorMinimo cupom2 = new CupomValorMinimo("PROMO", 100, 2500);

        venda2.finalizarVenda();

    }
}