package com.fleet.vehicle_routing.config;



import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .name("Authorization")
                                .in(SecurityScheme.In.HEADER)))
                .info(new Info()
                        .title("APIs for School Timetable Project")
                        .version("1.0")
                        .description("APIs for School Timetable Project")
                        .contact(new Contact()
                                .name("Oodles Technologies")
                                .url("http://yourwebsite.com")
                                .email("your-email@example.com")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }


}
