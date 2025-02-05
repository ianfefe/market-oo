import Cupons.CupomInvalidoException;
import Cupons.CupomValorMinimo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CupomValorMinimoTeste {
    @Test
    public void isCupomValidoTeste(){
        CupomValorMinimo cupom = new CupomValorMinimo("CUPOM", 10, 10);
        boolean resultado = cupom.isCupomValido(20);
        assertTrue(resultado);
    }

    @Test
    public void isCupomInvalidoTeste(){
        CupomValorMinimo cupom = new CupomValorMinimo("CUPOM", 10,10);

        CupomInvalidoException cupomMinimoAbaixo = assertThrows(
                CupomInvalidoException.class,
                () -> cupom.isCupomValido(9)
        );

        assertEquals("O cupom não atende os requisitos para aplicação. " +
                "O valor mínimo da compra deve ser R$" + cupom.getValorMin(), cupomMinimoAbaixo.getMessage());
    }
}
