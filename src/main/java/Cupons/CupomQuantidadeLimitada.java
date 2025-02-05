package Cupons;

public class CupomQuantidadeLimitada extends Cupom {
    private final int usoMax;
    private int quantUsada = 0;

    public CupomQuantidadeLimitada(String codigo, double valorDesconto, int usoMax) {
        super(codigo, valorDesconto);
        if (usoMax <= 0) {
            this.ativo = false;
        }
        this.usoMax = usoMax;
    }

    @Override
    public boolean isCupomValido(double valor) {
        System.out.println("Cupom aplicado!");
        System.out.println("Você ganhou R$" + valorDesconto + " de desconto!");
        quantUsada++;

        if (quantUsada >= usoMax) {
            this.ativo = false;
            return false;
        }

        return true;
    }
}