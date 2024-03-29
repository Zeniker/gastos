package br.com.guilherme.gastos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SPRING_WEB)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.guilherme.gastos.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "API Gastos",
                "API para calcular gastos e ganhos",
                "0.0.1",
                "",
                null,
                "",
                "",
                new ArrayList<>()
        );
    }
}
