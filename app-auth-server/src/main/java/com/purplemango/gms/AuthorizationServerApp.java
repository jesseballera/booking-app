package com.purplemango.gms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.SpringApplication.run;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.purplemango.gms")
@OpenAPIDefinition(info = @Info(title = "GMS Authorization Server", version = "1.0", description = "API documentation for GMS Authorization Server"))
public class AuthorizationServerApp {
    public static void main(String[] args) {
        // Run the Spring Boot application
        run(AuthorizationServerApp.class, args);
    }
}
