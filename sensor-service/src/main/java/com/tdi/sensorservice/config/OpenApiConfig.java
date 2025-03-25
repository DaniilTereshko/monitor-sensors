package com.tdi.sensorservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API датчиков системы мониторинга")
                        .contact(new Contact()
                                .name("Daniil Tereshko")
                                .email("daniiltereshko123@gmail.com")));
    }

}