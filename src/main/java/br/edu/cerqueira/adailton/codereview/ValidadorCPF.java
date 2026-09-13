package br.edu.cerqueira.adailton.codereview;

import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

/**
 * Utilitário de validação de CPF.
 */
public class ValidadorCPF {
    public boolean ValidaCpf(String cpf) {
        if (cpf == null) {
            return false;
        }

        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (cpfLimpo.length() != 11) {
            return false;
        }

        if (cpfLimpo.equals("00000000000") || cpfLimpo.equals("11111111111")
                || cpfLimpo.equals("22222222222") || cpfLimpo.equals("33333333333")
                || cpfLimpo.equals("44444444444") || cpfLimpo.equals("55555555555")
                || cpfLimpo.equals("66666666666") || cpfLimpo.equals("77777777777")
                || cpfLimpo.equals("88888888888") || cpfLimpo.equals("99999999999")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (10 - i);
        }
        int resto = 11 - (soma % 11);
        int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (11 - i);
        }
        resto = 11 - (soma % 11);
        int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

        return Character.getNumericValue(cpfLimpo.charAt(9)) == digito1
                && Character.getNumericValue(cpfLimpo.charAt(10)) == digito2;
    }
}
