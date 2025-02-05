package Vendas;

import Cupons.Cupom;
import Cupons.CupomInvalidoException;
import Cupons.CupomQuantidadeLimitada;
import Produtos.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Venda {
    private double total = 0;
    private boolean finalizada = false;
    private Cupom cupomAplicado = null;
    private final int idUnico;
    private final List<Produto> listaProdutos = new ArrayList<>();
    private static int id = 1;

    public Venda() {
        this.idUnico = id;
        id++;
        GestorVendas.adicionaVenda(this);
    }

    public boolean getStatus() {
        return this.finalizada;
    }

    public Cupom getCupom() {
        return this.cupomAplicado;
    }

    public double getTotal() {
        return this.total;
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
                System.out.println(produto.getNome() + " removido da venda.\n");

                if(listaProdutos.isEmpty()){
                    removerCupom();
                }

                if (cupomAplicado != null) {
                    String codigoCupom = cupomAplicado.getCodigo();
                    removerCupom();
                    aplicarCupom(codigoCupom);
                }
            }
        }
    }

    public boolean aplicarCupom(String codigoCupom) throws CupomInvalidoException {
        if (!this.finalizada) {
            if (cupomAplicado != null) {
                throw new CupomInvalidoException("Não é possível sobrescrever cupom aplicado.\n");
            }
            for (Cupom cupom : Cupom.listaCupons) {
                if (cupom.getCodigo().equals(codigoCupom)) {
                    if (cupom.getStatus()) {
                        if (cupom.isCupomValido(total)) {
                            cupomAplicado = cupom;
                            break;
                        }else{
                            throw new CupomInvalidoException("Cupom esgotado.\n");
                        }
                    } else {
                        throw new CupomInvalidoException("Cupom esgotado.\n");
                    }
                }
            }

            if (cupomAplicado == null) {
                throw new CupomInvalidoException("Cupom não encontrado.\n");
            }

            total -= cupomAplicado.getDesconto();
            if (total < 0) {
                total = 0;
            }
            return true;
        } return true;
    }

    public void removerCupom(){
        if(cupomAplicado == null){
            System.out.println("Nenhum cupom aplicado.\n");
        }else{
            total += cupomAplicado.getDesconto();
            if(cupomAplicado instanceof CupomQuantidadeLimitada){
                ((CupomQuantidadeLimitada) cupomAplicado).setQuantUsada(-1);
            }
            cupomAplicado = null;
            System.out.println("Cupom removido.\n");
        }
    }

    public void finalizarVenda() {
        if (!this.finalizada) {
            if (this.total == 0) {
                System.out.println("Não foi possivel finalizar a venda!");
                System.out.println("Nenhum produto adicionado!\n");
            } else {
                this.relatorioVenda();

                System.out.println("\nPara finalizar a venda digite (continuar)");
                Scanner scanner = new Scanner(System.in);
                String resposta = scanner.nextLine();

                if (resposta.equals("continuar")) {
                    finalizada = true;
                    System.out.println("\nVenda finalizada.\n");
                    this.relatorioVenda();
                }else{
                    System.out.println("Venda não finalizada, tente novamente.\n");
                }
            }
        }
    }

    public void relatorioVenda() {
        System.out.println("\n------- VENDA " + this.idUnico + " -------");
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto selecionado\n");
        }
        for (Produto produto : listaProdutos) {
            System.out.println(produto.getNome() + " - R$" + produto.getPreco());
        }
        System.out.println("Cupom aplicado: " + (cupomAplicado != null ? cupomAplicado.getCodigo() : "nenhum"));
        System.out.println("Total: R$" + total);
        System.out.println("-----------------------");
    }
}