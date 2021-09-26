package com.faculdade.consultapostos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConsultaPostosApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ConsultaPostosApplication.class, args);
    }

}
