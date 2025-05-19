package com.purplemango.gms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import static org.springframework.boot.SpringApplication.run;

@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApp {
    // http://localhost:8888/config-client/dev
    public static void main(String[] args) {
        run(ConfigServiceApp.class, args);
    }
}
