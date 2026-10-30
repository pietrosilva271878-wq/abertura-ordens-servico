package br.com.ordensservico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamentos")
public class Equipamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String numeroPatrimonio;
    private String categoria;

    private String descricao;


    // Construtor vazio
    public Equipamentos() {
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


     public String getNumeroPatrimonio(){
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio){
        this.numeroPatrimonio = numeroPatrimonio;
    }
}
