package br.edu.cerqueira.adailton.codereview;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de demonstração do laboratório. Não faz parte do escopo de
 * revisão, apenas exercita as classes do pacote para fins de teste manual.
 */
public class Main {

    public static void main(String[] args) {
        ValidadorCPF validadorCPF = new ValidadorCPF();
        System.out.println("CPF 123.456.789-09 valido? " + validadorCPF.ValidaCpf("12345678909"));

        ValidadorPedido validadorPedido = new ValidadorPedido();
        String resultado = validadorPedido.validar("SP", "CARTAO", 3, 650.0, false, null);
        System.out.println("Resultado do pedido: " + resultado);

        System.out.println("Frete SP (250,00 / 3 itens): " + validadorPedido.calcularFreteSudeste(250.0, 3));
        System.out.println("Frete RS (250,00 / 3 itens): " + validadorPedido.calcularFreteSul(250.0, 3));

        RelatorioVendasService relatorioVendasService = new RelatorioVendasService();
        List<RelatorioVendasService.Venda> vendas = new ArrayList<>();
        vendas.add(new RelatorioVendasService.Venda("Mouse", 89.90));
        vendas.add(new RelatorioVendasService.Venda("Teclado", 150.00));
        System.out.println("Total de vendas: " + relatorioVendasService.calcularTotal(vendas));
    }
}
