import Cupons.CupomQuantidadeLimitada;
import Produtos.Eletronico;
import Vendas.Venda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValorVendaTeste {
    @Test
    public void totalVendaSemCupomTeste() {
        Venda venda = new Venda();
        Eletronico celular = new Eletronico("Celular",1200,90);
        venda.adicionarProduto(celular);
        venda.adicionarProduto(celular);

        assertEquals(2400, venda.getTotal());
    }

    @Test
    public void totalVendaComCupomTeste() {
        Venda venda = new Venda();
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM9",250,1);
        Eletronico celular = new Eletronico("Celular",1200,90);
        venda.adicionarProduto(celular);
        venda.adicionarProduto(celular);

        venda.aplicarCupom("CUPOM9");

        assertEquals(2400-cupom.getDesconto(), venda.getTotal());
    }
}
