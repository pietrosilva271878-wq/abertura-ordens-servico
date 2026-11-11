
package br.com.ordensservico.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ordensservico.dto.EncerramentoOrdemRequest;
import br.com.ordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.model.OrdemServico;
import br.com.ordensservico.service.OrdemServicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(
            OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    // ==========================
    // CADASTRAR
    // ==========================
    @PostMapping
    public ResponseEntity<OrdemServico> cadastrar(
            @Valid @RequestBody OrdemServicoRequest dados) {

        OrdemServico ordem =
                ordemServicoService.cadastrar(dados);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ordem);
    }

    // ==========================
    // LISTAR
    // ==========================
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {

        return ResponseEntity.ok(
                ordemServicoService.listar()
        );
    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(
            @PathVariable Integer id) {

        return ordemServicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    // ==========================
    // ATUALIZAR
    // ==========================
    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody OrdemServicoRequest dados) {

        return ordemServicoService.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    // ==========================
    // DELETAR
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id) {

        boolean deletado =
                ordemServicoService.deletar(id);

        if (!deletado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // ==========================
    // ENCERRAR ORDEM DE SERVIÇO
    // ==========================
   @PutMapping("/{id}/encerramento")
public ResponseEntity<OrdemServico> encerrarOrdemServico(
        @PathVariable Integer id,
        @Valid @RequestBody EncerramentoOrdemRequest dados) {

    OrdemServico ordem = ordemServicoService.encerrarOrdemServico(
            id,
            dados.getSolucao()
    );

    return ResponseEntity.ok(ordem);
}
}
