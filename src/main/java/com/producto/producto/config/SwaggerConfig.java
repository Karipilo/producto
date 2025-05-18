package com.producto.producto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi(){
        return new OpenAPI().info(new Info()
        .title("Proyecto de productos")
        .version("1.0")
        .description("Este es el microservicio de productos")
        );
                
    }
}
