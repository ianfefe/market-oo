package Cupons;

public class CupomValorMinimo extends Cupom{
    double valorMin;

    public CupomValorMinimo(String codigo, double valorDesconto , double valorMin) {
        super(codigo, valorDesconto);
        this.valorMin = valorMin;
    }

    @Override
    public boolean eValido(double valor) {
        if(!(valorMin <= valor)){
            throw new CupomInvalidoException("O cupom não atende os requisitos para aplicação. " +
                    "O valor mínimo da compra deve ser R$" + valorMin);
        }
        return true;
    }
}
