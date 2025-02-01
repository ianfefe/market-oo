package Cupons;

public class CupomQuantidadeLimitada extends Cupom{
    private final int usoMax;
    private int quantUsada = 0;

    public CupomQuantidadeLimitada(String codigo, double valorDesconto , int usoMax) {
        super(codigo, valorDesconto);
        this.usoMax = usoMax;
    }

    @Override
    public boolean eValido(double valor) {
        if(!(usoMax > quantUsada)){
            throw new CupomInvalidoException("O limite de usos do cupom já foi atingido.");
        }
        System.out.println("Cupom aplicado!");
        quantUsada++;
        return true;
    }
}
