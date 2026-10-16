package br.edu.cerqueira.adailton.codereview;

/**
 * Utilitário de validação de CPF.
 */
public class ValidadorCPF {
    public boolean validaCpf(String cpf) {
        if (cpf == null) {
            return false;
        }

        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (cpfLimpo.length() != 11) {
            return false;
        }

        if (checaDigitoRepetido(cpfLimpo)) {
            return false;
        }

        return checaDigitoVerificador(cpfLimpo, 9) && checaDigitoVerificador(cpfLimpo, 10);
    }

    private boolean checaDigitoRepetido(String cpf) {
        char primeiroDigito = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiroDigito) {
                return false;
            }
        }
        return true;
    }

    private boolean checaDigitoVerificador(String cpf, int digito) {
        int soma = 0;
        for (int i = 0; i < digito; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (digito + 1 - i);
        }
        int resto = 11 - (soma % 11);
        return Character.getNumericValue(cpf.charAt(digito)) == ((resto == 10 || resto == 11) ? 0 : resto);
    }
}
