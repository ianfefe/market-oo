package Vendas;

import Cupons.Cupom;

import java.util.ArrayList;
import java.util.List;

public class GestorVendas implements Relatorio {
    private static final List<Venda> listaVendas = new ArrayList<>();

    protected static void adicionaVenda(Venda venda) {
        listaVendas.add(venda);
    }

    @Override
    public void gerarRelatorioVendas() {
        System.out.println("\n------- RELATORIO DE VENDAS -------\n");
        for (Venda venda : listaVendas) {
            venda.relatorioVenda();
            System.out.println("STATUS: " + (venda.getStatus() ? "FINALIZADA" : "EM ANDAMENTO"));
        }
        System.out.println("-----------------------------------\n");
    }

    @Override
    public void listarCuponsAtivos() {
        System.out.println("---- CUPONS ATIVOS ----");
        for (Cupom cupom : Cupom.listaCupons) {
            if (cupom.getStatus()) {
                System.out.println(cupom.getCodigo());
            }
        }
        System.out.println("-----------------------");
    }
}
