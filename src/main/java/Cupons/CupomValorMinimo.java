package Cupons;

public class CupomValorMinimo extends Cupom{
    private double valorMin;

    public CupomValorMinimo(String codigo, double valorDesconto , double valorMin) {
        super(codigo, valorDesconto);
        this.valorMin = valorMin;
    }

    public double getValorMin(){
        return this.valorMin;
    }

    @Override
    public boolean isCupomValido(double valor) {
        if(!(valorMin <= valor)){
            throw new CupomInvalidoException("O cupom não atende os requisitos para aplicação. " +
                    "O valor mínimo da compra deve ser R$" + valorMin);
        }
        return true;
    }
}
