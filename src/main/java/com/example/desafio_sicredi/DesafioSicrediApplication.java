package com.example.desafio_sicredi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Desafio Sicredi", version = "1.0.0", description = "API para gerenciamento de sessões de votação no Desafio Sicredi"))
public class DesafioSicrediApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioSicrediApplication.class, args);
    }

}
