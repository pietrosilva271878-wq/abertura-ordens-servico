package br.com.ordensservico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "setores")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotBlank (message = "O nome do setor é obrigatório")
    private String nome;

    private Integer id;
    

    // Construtor vazio obrigatório para o JPA
    public Setor() {
    }


    // Construtor
    public Setor(String nome) {
        this.nome = nome;
    }


    // GET e SET do ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    // GET e SET do NOME
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}