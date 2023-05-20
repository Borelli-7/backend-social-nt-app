package com.kaly7dev.socialntapp.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI expenseAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Social Network API")
                        .description("API for Social Network Application")
                        .version("0.0.1")
                        .license(new License()
                                .name("Apache license Version 2.0")
                                .url("https://kaly7dev.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Externals documentation"));
    }
}
