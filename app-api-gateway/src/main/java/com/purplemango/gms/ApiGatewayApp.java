package com.purplemango.gms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.http.HttpMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.boot.SpringApplication.run;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.purplemango.gms")
@OpenAPIDefinition(info = @Info(title = "GMS API Gateway", version = "1.0", description = "API documentation for GMS API Gateway"))
public class ApiGatewayApp {
    public static void main(String[] args) {
        // Run the Spring Boot application
        run(ApiGatewayApp.class, args);
    }

//    @Bean
//    @Lazy(false)
//    public Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> apis(RouteDefinitionLocator locator,
//                                                                  SwaggerUiConfigParameters swaggerUiConfigParameters) {
//        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
//        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
//            String name = routeDefinition.getId().replaceAll("-service", "");
//            AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl(name, "/" + name, null);
//            urls.add(swaggerUrl);
//        });
//        swaggerUiConfigParameters.setUrls(urls);
//        return urls;
//    }

//    @Bean
//    public  StandardReflectionParameterNameDiscoverer getStandardReflectionParameterNameDiscoverer() {
//        return new StandardReflectionParameterNameDiscoverer();
//    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r
                        .path("/iam-service/v3/api-docs")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://iam-service"))
//                .route(r -> r
//                        .path("/gateway-service/v3/api-docs")
//                        .and()
//                        .method(HttpMethod.GET)
//                        .uri("http://localhost:8060"))
//                .route(r -> r
//                        .path("/api/v1/**")
//                        .filters(filter -> {
//                            filter.addResponseHeader("res-header", "res-header-value");
//                            return filter;
//                        })
//                        .uri("lb://iam-service"))
//                .route(r -> r
//                        .path("/api/v1/**")
//                        .filters(filter -> {
//                            filter.addResponseHeader("res-header", "res-header-value");
//                            return filter;
//                        })
//                        .uri("lb://health-service"))

//                .route(r -> r
//                        .path("/data-service/v3/api-docs")
//                        .and()
//                        .method(HttpMethod.GET)
//                        .uri("lb://data-service"))
//                .route(r -> r
//                        .path("/api/v1/**")
//                        .filters(filter -> {
//                            filter.addResponseHeader("res-header", "res-header-value");
//                            return filter;
//                        })
//                        .uri("lb://data-service"))

//                .route(r -> r
//                        .path("/game-service/v3/api-docs")
//                        .and()
//                        .method(HttpMethod.GET)
//                        .uri("lb://game-service"))
//                .route(r -> r
//                        .path("/game/v1/**")
//                        .filters(filter -> {
//                            filter.addResponseHeader("res-header", "res-header-value");
//                            return filter;
//                        })
//                        .uri("lb://game-service"))

//                .route(r -> r
//                        .path("/authorization-server/v3/api-docs")
//                        .and()
//                        .method(HttpMethod.GET)
//                        .uri("lb://authorization-server"))
//                .route(r -> r
//                        .path("/api/login/**")
//                        .and()
//                        .method(HttpMethod.POST)
//                        .uri("lb://authorization-server"))

                .build();
    }
}
