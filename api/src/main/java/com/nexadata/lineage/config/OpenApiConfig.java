package com.nexadata.lineage.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Nexa Lineage API")
                .description("Track data flow across your entire data ecosystem")
                .version("1.0.0")
                .contact(
                    new Contact()
                        .name("Nexa Lineage Team")
                        .url("https://github.com/swar00pduthks/nexadatamesh-lineage"))
                .license(
                    new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")))
        .servers(
            List.of(
                new Server().url("http://localhost:8080").description("Development server"),
                new Server().url("https://api.nexa-lineage.com").description("Production server")));
  }
}
