package br.com.ordensservico.dto;

import jakarta.validation.constraints.NotBlank;

public class EncerramentoOrdemRequest {

    @NotBlank(message = "A solução é obrigatória")
    private String solucao;

    public EncerramentoOrdemRequest() {
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

}
