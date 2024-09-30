package com.example.desafio_sicredi.util;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<ValidCPF, String> {

    // Padrão para 11 dígitos sem pontuação
    private static final String CPF_REGEX = "^[0-9]{11}$";

    @Override
    public void initialize(ValidCPF constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null) {
            return false;
        }
        // Remove pontos e hífen do CPF, caso existam, e verifica o formato
        String sanitizedCPF = cpf.replaceAll("[^0-9]", "");
        return sanitizedCPF.matches(CPF_REGEX) && isValidCPF(sanitizedCPF);
    }

    private boolean isValidCPF(String cpf) {
        // Verifica se todos os dígitos são iguais, o que invalida o CPF (ex: "111.111.111-11")
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }
        // Cálculo do primeiro dígito verificador
        int firstDigit = calculateDigit(cpf, 9, 10);
        // Cálculo do segundo dígito verificador
        int secondDigit = calculateDigit(cpf, 10, 11);
        // Verifica se os dígitos verificadores são iguais aos calculados
        return cpf.charAt(9) - '0' == firstDigit && cpf.charAt(10) - '0' == secondDigit;
    }

    // Método auxiliar para calcular o dígito verificador
    private int calculateDigit(String cpf, int length, int weight) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += (cpf.charAt(i) - '0') * weight--;
        }

        int result = 11 - (sum % 11);
        return result >= 10 ? 0 : result;
    }
}

