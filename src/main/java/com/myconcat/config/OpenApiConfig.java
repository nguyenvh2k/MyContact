package com.myconcat.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI concatOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Contact API")
                        .description("My contact sample application")
                        .version("v0.0.1")
                        .contact(new Contact().email("nguyenvh2k@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://smile1001.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("My Contact Documentation")
                        .url("https://smile1001.com/docs"));
    }
}
