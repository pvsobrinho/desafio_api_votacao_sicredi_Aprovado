package com.example.desafio_sicredi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class CpfValidationService {

    // Simulação de URL, mas não validaremos diretamente com randomuser
    private static final String RANDOM_USER_API = "https://randomuser.me/api/";

    public boolean isAbleToVote(String cpf) {
        // Simulação: Se o último dígito do CPF for par, o CPF é considerado válido
        int lastDigit = Integer.parseInt(cpf.substring(cpf.length() - 1));

        // Faz uma chamada à API randomuser.me apenas para simular uma requisição externa
        // OBS:  Não encontrei opções gratuitas para enviar um numero de CPF e fazer esta validação.
        // De toda forma está testando uma chaamda externa.

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RandomUserResponse> response = restTemplate.getForEntity(RANDOM_USER_API, RandomUserResponse.class);

        // Apenas para adicionar a lógica externa (neste caso, pegamos o gênero)
        String gender = response.getBody().getResults().get(0).getGender();

        // A lógica de "pode votar" será com base no último dígito do CPF e o gênero retornado pela API
        return lastDigit % 2 == 0 && "male".equalsIgnoreCase(gender);
    }

    // Classe interna para mapear a resposta da API Random User
    public static class RandomUserResponse {
        private List<UserResult> results;

        public List<UserResult> getResults() {
            return results;
        }

        public void setResults(List<UserResult> results) {
            this.results = results;
        }
    }

    public static class UserResult {
        private String gender;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
