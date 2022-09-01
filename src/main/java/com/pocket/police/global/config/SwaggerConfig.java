package com.pocket.police.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
               .group("hanium-api-v1")
               .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI policeInMyPocketOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Polinpo API")
                        .description("2022 한이음 프로젝트 API 명세서")
                        .version("v0.0.1")
                );
    }
}
