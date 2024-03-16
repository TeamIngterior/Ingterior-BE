package com.team_ingterior.ingterior.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
    .info(new Info()
    .title("IngTerior")
    .description("IngTerior API Test")
    .version("1.0.0"));
  }

}