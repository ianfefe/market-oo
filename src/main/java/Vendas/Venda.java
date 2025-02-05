package Vendas;

import Cupons.Cupom;
import Cupons.CupomInvalidoException;
import Produtos.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Venda {
    public double total = 0;
    private boolean finalizada = false;
    private Cupom cupomAplicado = null;
    private final int idUnico;
    private final List<Produto> listaProdutos = new ArrayList<>();
    private static int id = 0;

    public Venda() {
        this.idUnico = id;
        id++;
        GestorVendas.adicionaVenda(this);
    }

    protected boolean getStatus() {
        return this.finalizada;
    }

    public void adicionarProduto(Produto produto) {
        if (!this.finalizada) {
            listaProdutos.add(produto);
            total += produto.getPreco();
        }
    }

    public void removerProduto(Produto produto) {
        if (!this.finalizada) {
            if (listaProdutos.contains(produto)) {
                listaProdutos.remove(produto);
                total -= produto.getPreco();
                System.out.println(produto.getNome() + " removido da venda");

                if (cupomAplicado != null) {
                    total += cupomAplicado.getDesconto();
                    aplicarCupom(cupomAplicado.getCodigo());
                }
            }
        }
    }

    public boolean aplicarCupom(String codigoCupom) throws CupomInvalidoException {
        if (cupomAplicado != null) {
            throw new CupomInvalidoException("Não é possível sobrescrever cupom aplicado.");
        }
        for (Cupom cupom : Cupom.listaCupons) {
            if (cupom.getCodigo().equals(codigoCupom)) {
                if (cupom.getStatus()) {
                    if (cupom.isCupomValido(total)) {
                        cupomAplicado = cupom;
                        break;
                    }else{
                        throw new CupomInvalidoException("Cupom esgotado.");
                    }
                } else {
                    throw new CupomInvalidoException("Cupom esgotado.");
                }
            }
        }

        if (cupomAplicado == null) {
            throw new CupomInvalidoException("Cupom não encontrado.");
        }

        total -= cupomAplicado.getDesconto();
        if (total < 0) {
            total = 0;
        }
        return true;
    }

    public void finalizarVenda() {
        if (!this.finalizada) {
            if (this.total == 0) {
                System.out.println("Não foi possivel finalizar a venda!");
                System.out.println("Nenhum produto adicionado!");
            } else {
                this.relatorioVenda();

                System.out.println("\nPara finalizar a venda digite (continuar)");
                System.out.println("Para cancelar digite (cancelar)");
                Scanner scanner = new Scanner(System.in);
                String resposta = scanner.nextLine();

                if (resposta.equals("cancelar")) {
                    System.out.println("Venda não finalizada, tente novamente.");
                } else if (resposta.equals("continuar")) {
                    finalizada = true;
                    System.out.println("\nVenda finalizada.");
                    this.relatorioVenda();
                }
            }
        }
    }

    public void relatorioVenda() {
        System.out.println("------- VENDA " + this.idUnico + " -------");
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto selecionado");
        }
        for (Produto produto : listaProdutos) {
            System.out.println(produto.getNome() + " - R$" + produto.getPreco());
        }
        System.out.println("Cupom aplicado: " + (cupomAplicado != null ? cupomAplicado.getCodigo() : "nenhum"));
        System.out.println("Total: R$" + total);
        System.out.println("-----------------------");
    }
}