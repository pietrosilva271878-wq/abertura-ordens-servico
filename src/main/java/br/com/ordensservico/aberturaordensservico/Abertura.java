package br.com.ordensservico.aberturaordensservico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.ordensservico")
@EnableJpaRepositories(basePackages = "br.com.ordensservico.repository")
@org.springframework.boot.persistence.autoconfigure.EntityScan (basePackages = "br.com.ordensservico.model")
public class Abertura {

    public static void main(String[] args) {
        SpringApplication.run(Abertura.class, args);
    }
}