import Cupons.CupomQuantidadeLimitada;
import Produtos.Eletronico;
import Vendas.Venda;

public class Main {
    public static void main(String[] args) {

        Eletronico celular = new Eletronico("celular", 1200, 90);
        Venda venda = new Venda();
        Venda venda2 = new Venda();
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM10",10,1);

        venda.adicionarProduto(celular);
        venda.adicionarProduto(celular);

        venda.aplicarCupom("CUPOM10");
        venda2.aplicarCupom("CUPOM10");


    }
}