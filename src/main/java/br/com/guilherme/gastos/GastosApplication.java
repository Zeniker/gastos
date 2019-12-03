package br.com.guilherme.gastos;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEncryptableProperties
//@EnableSwagger2
public class GastosApplication {

    public static void main(String[] args) {

        SpringApplication.run(GastosApplication.class, args);
    }

//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SPRING_WEB)
//                        .select()
//                        .apis(RequestHandlerSelectors.basePackage("br.com.guilherme.gastos.controller"))
//                        .paths(PathSelectors.any())
//                        .build();
//    }

}
