package br.com.guilherme.gastos;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class GastosApplication {

    public static void main(String[] args) {

        SpringApplication.run(GastosApplication.class, args);
    }

}
