import Cupons.CupomQuantidadeLimitada;
import Cupons.CupomValorMinimo;
import Produtos.*;
import Vendas.GestorVendas;
import Vendas.Venda;

public class Main {
    public static void main(String[] args) {

        //1 - Criação de alguns cupons, vendas e produtos para serem utilizados

        Eletronico celular = new Eletronico("Celular",1200,365);
        Alimento chocolate = new Alimento("Chocolate", 10, 7);
        Roupa camisa = new Roupa("Camisa", 45, TamanhosRoupa.G, CoresRoupa.PRETO);

        CupomQuantidadeLimitada cupomExclusivo = new CupomQuantidadeLimitada("DCC025",250,1);
        CupomValorMinimo cupomMinimo = new CupomValorMinimo("shopping",25,50);

        Venda vendaBarata = new Venda();
        Venda vendaCara = new Venda();


        //2 - Cadastro e remoção de produtos e cupons nas vendas

        vendaBarata.adicionarProduto(chocolate);
        vendaBarata.adicionarProduto(chocolate);
        vendaBarata.adicionarProduto(camisa);

        vendaBarata.removerProduto(chocolate);

        vendaBarata.aplicarCupom("shopping");


        vendaCara.adicionarProduto(celular);
        vendaCara.aplicarCupom("DCC025");


        //2.1 - Também é possivel finalizar a venda, para não ser modificada

        vendaBarata.finalizarVenda();
        vendaBarata.adicionarProduto(celular);


        //2.2 - Remoção do cupom e produto da venda também é possivel. (Quando o produto é removido o cupom também é para verificar os requisitos e reaplicar.

        vendaCara.removerProduto(celular);


        //3 - Gerar relatório único de cada venda

        vendaBarata.relatorioVenda();
        vendaCara.relatorioVenda();


        //4 - Gerar relatório de cupons ativos e vendas feitas (Gestor de vendas)

        GestorVendas gleiph = new GestorVendas();

        gleiph.listarCuponsAtivos();
        gleiph.gerarRelatorioVendas();
    }
}