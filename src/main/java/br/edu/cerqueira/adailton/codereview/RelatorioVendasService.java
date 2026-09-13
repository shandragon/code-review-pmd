package br.edu.cerqueira.adailton.codereview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço de geração de relatório de vendas a partir de um arquivo texto.
 */
public class RelatorioVendasService {

    /**
     * Lê um arquivo de vendas (uma venda por linha, formato "produto;valor")
     * e retorna a lista de vendas processadas.
     */
    public List<Venda> processarArquivo(String caminhoArquivo) {
        List<Venda> vendas = new ArrayList<>();

        try {
            FileReader fr = new FileReader(caminhoArquivo);
            BufferedReader br = new BufferedReader(fr);

            String linha = br.readLine();
            while (linha != null) {
                Venda venda = converterLinha(linha);
                vendas.add(venda);
                linha = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }

        return vendas;
    }

    private Venda converterLinha(String linha) {
        String[] partes = linha.split(";");
        String produto = partes[0];

        Double valor = null;
        if (partes.length > 1) {
            valor = Double.parseDouble(partes[1]);
        }
        return new Venda(produto, valor);
    }

    /**
     * Calcula o total de vendas somando os valores.
     */
    public double calcularTotal(List<Venda> vendas) {
        double total = 0;
        for (Venda venda : vendas) {
            total += venda.getValor();
        }
        return total;
    }

    /**
     * Aplica desconto a uma venda.
     */
    public double aplicarDesconto(double valor, boolean clienteVip) {
        double resultado = valor;
        int contadorAuxiliar = 0;

        if (clienteVip) {
            resultado = valor * 0.9;
        } else {
            resultado = valor;
        }
        return resultado;
    }

    /**
     * Método de cálculo de desconto antigo.
     */
    private double calcularDescontoAntigo(double valor) {
        return valor * 0.95;
    }

    public static class Venda {
        private String produto;
        private Double valor;

        public Venda(String produto, Double valor) {
            this.produto = produto;
            this.valor = valor;
        }

        public String getProduto() {
            return produto;
        }

        public Double getValor() {
            return valor;
        }
    }
}
