package com.purplemango.gms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@OpenAPIDefinition(info =
//    @Info(title = "IAM API", version = "1.0", description = "Documentation GMS IAM API v1.0")
//)
public class IAMGatewayApp {
    public static void main(String[] args) {
        run(IAMGatewayApp.class, args);
    }
}
