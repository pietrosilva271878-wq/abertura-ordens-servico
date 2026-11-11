package br.com.ordensservico.aberturaordensservico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.ordensservico")
@EntityScan(basePackages = "br.com.ordensservico.model")
@EnableJpaRepositories(basePackages = "br.com.ordensservico.repository")
public class Abertura {

    public static void main(String[] args) {
        SpringApplication.run(Abertura.class, args);
    }
}