import Cupons.CupomInvalidoException;
import Cupons.CupomQuantidadeLimitada;
import Cupons.CupomValorMinimo;
import Vendas.Venda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AplicarCupomTeste {

    @Test
    public void cupomInexistenteTeste() {
        Venda venda = new Venda();

        CupomInvalidoException cupomInexistente = assertThrows(
                CupomInvalidoException.class,
                () -> venda.aplicarCupom("CUPOM2")
        );

        assertEquals("Cupom não encontrado.\n", cupomInexistente.getMessage());

    }

    @Test
    public void cupomJaAplicadoTeste() {
        CupomValorMinimo cupom = new CupomValorMinimo("CUPOM3",10,0);
        Venda venda = new Venda();
        venda.aplicarCupom("CUPOM3");

        CupomInvalidoException cupomSobrescrever = assertThrows(
                CupomInvalidoException.class,
                () -> venda.aplicarCupom("CUPOM3")
        );

        assertEquals("Não é possível sobrescrever cupom aplicado.\n", cupomSobrescrever.getMessage());

    }

    @Test
    public void cupomEsgotado() {
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM4",10,0);
        Venda venda = new Venda();

        CupomInvalidoException cupomLimitado = assertThrows(
                CupomInvalidoException.class,
                () -> venda.aplicarCupom("CUPOM4")
        );

        assertEquals("Cupom esgotado.\n", cupomLimitado.getMessage());
    }
}
