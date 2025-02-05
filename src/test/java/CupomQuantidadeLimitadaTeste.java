import Cupons.CupomQuantidadeLimitada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CupomQuantidadeLimitadaTeste {
    @Test
    public void isCupomValidoTeste(){
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM", 10, 10);
        boolean resultado = cupom.isCupomValido(20);
        assertTrue(resultado);
    }

    @Test
    public void isCupomInvalidoTeste(){
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM", 10,0);
        boolean resultado = cupom.isCupomValido(20);
        assertFalse(resultado);
    }
}
