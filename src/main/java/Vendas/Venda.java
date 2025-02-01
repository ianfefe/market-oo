package Vendas;

import Cupons.Cupom;
import Cupons.CupomInvalidoException;
import Produtos.Produto;
import Produtos.ProdutoInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Venda{
    private static int id = 0;
    private int idUnico;
    private boolean finalizada = false;
    public double total = 0;
    public Cupom cupomAplicado;
    private List<ProdutoInterface> listaProdutos = new ArrayList<>();

    public Venda(){
        this.idUnico = id;
        id++;
        GestorVendas.adicionaVenda(this);
    }

    protected boolean getStatus(){
        return this.finalizada;
    }

    public void adicionarProduto(Produto produto){
        if(!this.finalizada){
            listaProdutos.add(produto);
            total += produto.getPreco();
        }
    }

    public void removerProduto(Produto produto){
        if(!this.finalizada){
            if (listaProdutos.contains(produto)) {
                listaProdutos.remove(produto);
                total -= produto.getPreco();
                System.out.println(produto + " removido da venda");
            }
        }
    }

    private boolean aplicarCupom(String codigoCupom) throws CupomInvalidoException{
        for (Cupom cupom : Cupom.listaCupons){
            if(cupom.getCodigo().equals(codigoCupom)){
                if(cupom.getStatus()){
                    if(cupom.eValido(total)){
                        cupomAplicado = cupom;
                        break;
                    }
                }else{
                    throw new CupomInvalidoException("Cupom esgotado.");
                }
            }
        }if(cupomAplicado == null){
            throw new CupomInvalidoException("Cupom não encontrado");
        }
        total -= cupomAplicado.getDesconto();
        return true;
    }

    public void finalizarVenda(){
        if(!this.finalizada){
            this.relatorioVenda();

            System.out.println("\nPara finalizar a venda digite seu cupom ou escreva (continuar)");
            System.out.println("Para cancelar digite (cancelar)");
            Scanner scanner = new Scanner(System.in);
            String codigoCupom = scanner.nextLine();

            if (codigoCupom.equals("continuar") || aplicarCupom(codigoCupom)) {
                finalizada = true;
                System.out.println("\nVenda finalizada.");
                this.relatorioVenda();
            }else if(codigoCupom.equals("cancelar")){
                System.out.println("Venda não finalizada, tente novamente.");
            }
        }
    }

    public void relatorioVenda() {
        System.out.println("------- VENDA " + this.idUnico + " -------");
        for(ProdutoInterface produto: listaProdutos){
            System.out.println(produto.getNome() + " - R$" + produto.getPreco());
        }
        System.out.println("Cupom aplicado: " + (cupomAplicado != null ? cupomAplicado.getCodigo() : "nenhum"));
        System.out.println("Total: R$" + total);
        System.out.println("-----------------------");
    }
}