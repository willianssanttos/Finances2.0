package br.com.sistema.controle.financas.pessoais.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI configOpenApi(){
        return new OpenAPI().info(
                new Info().description("Definiçao para Api da plataforma Finances")
                        .version("1.0.0")
                        .title("Validações Api")
        );
    }
}
