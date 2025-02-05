package Cupons;

public class CupomValorMinimo extends Cupom {
    private final double valorMin;

    public CupomValorMinimo(String codigo, double valorDesconto, double valorMin) {
        super(codigo, valorDesconto);
        this.valorMin = valorMin;
    }

    public double getValorMin() {
        return this.valorMin;
    }

    @Override
    public boolean isCupomValido(double valor) {
        if (!(valorMin <= valor)) {
            throw new CupomInvalidoException("O cupom não atende os requisitos para aplicação. " +
                    "O valor mínimo da compra deve ser R$" + valorMin);
        }
        System.out.println("Cupom aplicado!");
        System.out.println("Você ganhou R$" + valorDesconto + " de desconto!");
        return true;
    }
}
