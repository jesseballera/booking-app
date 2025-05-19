package com.purplemango.gms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication(scanBasePackages = "com.purplemango.gms")
public class DiscoveryServiceApp {
    public static void main(String[] args) {
        // Run the Spring Boot application
        new SpringApplicationBuilder(DiscoveryServiceApp.class)
                .run(args);
    }
}
