package org.mitit.keu.configuration;

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
    public OpenAPI keuOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E.QUEUE REST API")
                        .description("APIs for E.QUEUE")
                        .version("1.0")
                        .termsOfService("Terms of service")
                        .contact(new Contact()
                                .name("Jam")
                                .url("https://www.viti.edu.ua")
                                .email("test@emaildomain.com"))
                        .license(new License()
                                .name("JAM license")
                                .url("https://www.viti.edu.ua/license")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Wiki")
                        .url("https://www.viti.edu.ua/wiki"));
    }
}
