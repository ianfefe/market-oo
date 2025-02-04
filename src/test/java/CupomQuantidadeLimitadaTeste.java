import Cupons.CupomInvalidoException;
import Cupons.CupomQuantidadeLimitada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CupomQuantidadeLimitadaTeste {
    @Test
    public void isCupomValidoTeste(){
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM", 10, 10);
        boolean resultado = cupom.isCupomValido(20);
        assertEquals(true, resultado, "Cupom aplicado!");
    }

    @Test
    public void isCupomInvalidoTeste(){
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("CUPOM", 10,0);

        CupomInvalidoException cupomUsadoMaximo = assertThrows(
                CupomInvalidoException.class,
                () -> cupom.isCupomValido(10)
        );

        assertEquals("O limite de usos do cupom já foi atingido.", cupomUsadoMaximo.getMessage());
    }
}
