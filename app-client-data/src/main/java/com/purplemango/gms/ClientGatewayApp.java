package com.purplemango.gms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import static org.springframework.boot.SpringApplication.run;

@EnableKafka
@SpringBootApplication
public class ClientGatewayApp {
    public static void main(String[] args) {
        run(ClientGatewayApp.class, args);
    }
}
