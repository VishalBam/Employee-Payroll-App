package com.employee.payroll.Config;  // Your base package

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Employee Payroll API")
                .version("1.0.0")
                .description("REST API for Employee Payroll Management"));
    }
}