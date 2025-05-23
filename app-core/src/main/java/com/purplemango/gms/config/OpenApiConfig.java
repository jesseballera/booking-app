package com.purplemango.gms.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${openapi.service.title}") String title,
                           @Value("${openapi.service.version}") String version,
                           @Value("${openapi.service.url}") String url,
                           @Value("${openapi.service.description}") String description) {
        final String securitySchemeName = "bearerAuth";
       return new OpenAPI()
               .servers(List.of(new Server().url(url)))
               .components(
                       new Components()
                               .addSecuritySchemes(
                                       securitySchemeName,
                                       new SecurityScheme()
                                               .type(SecurityScheme.Type.HTTP)
                                               .scheme("bearer")
                                               .bearerFormat("JWT")))
               .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
               .info(new Info().title(title).version(version).description(description));

    }
}
