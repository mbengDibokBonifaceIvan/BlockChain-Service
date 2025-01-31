package com.ivan.blockchain.Configurations.Swagger_Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("API de Traçabilité Blockchain")
                                                .version("2.0")
                                                .description("API gérant la traçabilité des Ressources et des Opérations Commerciales en enregistrant leurs états au fil du temps")
                                                .contact(new Contact()
                                                                .name("Équipe Service Blockchain")
                                                                .email("mbengivan63@gmail.com")
                                                                .url("https://github.com/mbengDibokBonifaceIvan/BlockChain-Service"))
                                                .license(new License()
                                                                .name("Apache 2.0")
                                                                .url("http://springdoc.org")))
                                .addServersItem(new Server()
                                                .url("http://localhost:8080")
                                                .description("Serveur local de développement"));
        }
}