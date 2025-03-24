package com.tdi.sensorservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final BuildProperties buildProperties;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(buildProperties.getArtifact())
                        .description("API датчиков системы мониторинга")
                        .version(buildProperties.getVersion())
                        .contact(new Contact()
                                .name("Daniil Tereshko")
                                .email("daniiltereshko123@gmail.com")));
    }

}