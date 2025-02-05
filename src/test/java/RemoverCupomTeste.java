import Cupons.Cupom;
import Cupons.CupomQuantidadeLimitada;
import Vendas.Venda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RemoverCupomTeste {
    @Test
    public void removeCupomTeste(){
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM",1,1);
        Venda venda = new Venda();
        venda.aplicarCupom("CUPOM");
        venda.removerCupom();

        Cupom temCupom = venda.getCupom();

        assertNull(temCupom);
    }

    @Test
    public void removeCupomTeste2(){
        Venda venda = new Venda();
        venda.removerCupom();

        Cupom temCupom = venda.getCupom();

        assertNull(temCupom);
    }
}
