package br.edu.cerqueira.adailton.codereview;

/**
 * Validador de pedidos de e-commerce.
 */
public class ValidadorPedido {

    /**
     * Valida um pedido considerando estado, forma de pagamento,
     * quantidade de itens, valor total, se é cliente novo e se há
     * cupom aplicado.
     */
    public String validar(String estado, String formaPagamento, int quantidadeItens,
                           double valorTotal, boolean clienteNovo, String cupom) {

        String resultado = "";

        if (estado != null) {
            if (estado.equals("SP") || estado.equals("RJ") || estado.equals("MG")) {
                if (formaPagamento != null) {
                    if (formaPagamento.equals("CARTAO")) {
                        if (valorTotal > 0) {
                            if (quantidadeItens > 0) {
                                if (clienteNovo) {
                                    if (cupom != null && !cupom.isEmpty()) {
                                        if (cupom.equals("BEMVINDO10")) {
                                            resultado = "APROVADO_COM_DESCONTO_NOVO";
                                        } else {
                                            resultado = "APROVADO_CUPOM_INVALIDO";
                                        }
                                    } else {
                                        resultado = "APROVADO_NOVO_SEM_CUPOM";
                                    }
                                } else {
                                    if (valorTotal > 500) {
                                        resultado = "APROVADO_FRETE_GRATIS";
                                    } else {
                                        resultado = "APROVADO_FRETE_PAGO";
                                    }
                                }
                            } else {
                                resultado = "REJEITADO_SEM_ITENS";
                            }
                        } else {
                            resultado = "REJEITADO_VALOR_INVALIDO";
                        }
                    } else if (formaPagamento.equals("BOLETO")) {
                        if (valorTotal > 0) {
                            if (quantidadeItens > 0) {
                                resultado = "APROVADO_BOLETO";
                            } else {
                                resultado = "REJEITADO_SEM_ITENS";
                            }
                        } else {
                            resultado = "REJEITADO_VALOR_INVALIDO";
                        }
                    } else {
                        resultado = "REJEITADO_PAGAMENTO_INVALIDO";
                    }
                } else {
                    resultado = "REJEITADO_SEM_PAGAMENTO";
                }
            } else {
                resultado = "REJEITADO_ESTADO_NAO_ATENDIDO";
            }
        } else {
            resultado = "REJEITADO_ESTADO_NULO";
        }

        return resultado;
    }

    /**
     * Calcula o frete para pedidos do sudeste.
     */
    public double calcularFreteSudeste(double valorTotal, int quantidadeItens) {
        double frete = 10.0;
        if (valorTotal > 500) {
            frete = 0.0;
        } else if (valorTotal > 200) {
            frete = 5.0;
        } else {
            frete = 10.0 + (quantidadeItens * 0.5);
        }
        return frete;
    }

    /**
     * Calcula o frete para pedidos do sul.
     */
    public double calcularFreteSul(double valorTotal, int quantidadeItens) {
        double frete = 15.0;
        if (valorTotal > 500) {
            frete = 0.0;
        } else if (valorTotal > 200) {
            frete = 8.0;
        } else {
            frete = 15.0 + (quantidadeItens * 0.5);
        }
        return frete;
    }
}
