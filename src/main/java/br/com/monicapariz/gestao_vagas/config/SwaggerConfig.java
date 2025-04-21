package br.com.monicapariz.gestao_vagas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI criaOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info().title("Gestão de Vagas")
                                .description("API responsável pela gestão de vagas")
                                .version("1")
                )
                .schemaRequirement("jwt_auth", criaSecurityScheme());
    }

    private SecurityScheme criaSecurityScheme() {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .name("jwt_auth")
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

}
