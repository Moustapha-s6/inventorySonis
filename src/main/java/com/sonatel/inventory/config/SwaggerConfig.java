package com.sonatel.inventory.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Dynamique Sonis")
                        .version("2.0.0")
                        .description("API qui gère dynamiquement les endpoints sur l'outil d'inventaire de Sonis"));
    }
}
