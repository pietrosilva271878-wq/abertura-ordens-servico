package br.com.ordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ordensservico.model.Setor;
import br.com.ordensservico.service.SetorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrar(
            @Valid @RequestBody Setor setor) {

        Setor novoSetor =
                setorService.cadastrar(setor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoSetor);
    }

    @GetMapping
    public ResponseEntity<List<Setor>> listar() {

        return ResponseEntity.ok(
                setorService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(
            @PathVariable Integer id) {

        Optional<Setor> setor =
                setorService.buscarPorId(id);

        if (setor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(setor.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Setor setor) {

        Optional<Setor> atualizado =
                setorService.atualizar(id, setor);

        if (atualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id) {

        boolean deletado =
                setorService.deletar(id);

        if (!deletado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}