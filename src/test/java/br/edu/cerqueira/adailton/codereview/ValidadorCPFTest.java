package br.edu.cerqueira.adailton.codereview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ValidadorCPFTest {
    private ValidadorCPF validadorCPF;
    
    @BeforeEach
    public void setup() {
        this.validadorCPF = new ValidadorCPF();
    }
    
    @Test
    public void deveValidarCPF() {
        assertTrue(this.validadorCPF.ValidaCpf("12345678909"));
        assertTrue(this.validadorCPF.ValidaCpf("52998224725"));
    }

    @Test
    public void deveValidarCPFComMascara() {
        assertTrue(this.validadorCPF.ValidaCpf("123.456.789-09"));
        assertTrue(this.validadorCPF.ValidaCpf("529.982.247-25"));
    }

    @Test
    public void deveInvalidarCPF() {
        assertFalse(this.validadorCPF.ValidaCpf("12345678908"));
        assertFalse(this.validadorCPF.ValidaCpf("52998224724"));
    }

    @ParameterizedTest(name = "[{index}] válido: {0}")
    @ValueSource(strings = {"12345678909", "52998224725", "123.456.789-09", "529.982.247-25"})
    public void deveValidarCPFParametro(String cpf) {
        assertTrue(this.validadorCPF.ValidaCpf(cpf));
    }

    @ParameterizedTest(name = "[{index}] entrada ausente: \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    public void deveRejeitarEntradaAusente(String cpf) {
        assertFalse(this.validadorCPF.ValidaCpf(cpf));
    }

    @ParameterizedTest(name = "[{index}] comprimento inválido: {0}")
    @ValueSource(strings = {"5299822472", "529982247250"})
    public void deveRejeitarComprimentoDiferenteDeOnze(String cpf) {
        assertFalse(this.validadorCPF.ValidaCpf(cpf));
    }
}
