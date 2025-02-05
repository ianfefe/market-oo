package Cupons;

import java.util.ArrayList;
import java.util.List;

public abstract class Cupom {
    public static List<Cupom> listaCupons = new ArrayList<>();
    protected boolean ativo = true;
    protected double valorDesconto;
    private final String codigo;

    protected Cupom(String codigo, double valorDesconto) {
        this.codigo = codigo;
        this.valorDesconto = valorDesconto;
        listaCupons.add(this);

    }

    public String getCodigo() {
        return this.codigo;
    }

    public double getDesconto() {
        return this.valorDesconto;
    }

    public boolean getStatus() {
        return this.ativo;
    }

    public abstract boolean isCupomValido(double valor);
}
