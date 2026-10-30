package br.com.ordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // CADASTRAR
    // POST /setores
    @PostMapping
    public ResponseEntity<Setor> cadastrar(
            @Valid @RequestBody Setor setor) {

        Setor setorCadastrado = setorService.cadastrar(setor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(setorCadastrado);
    }

    // LISTAR
    // GET /setores
    @GetMapping
    public ResponseEntity<List<Setor>> listar() {

        List<Setor> setores = setorService.listar();

        return ResponseEntity.ok(setores);
    }

    // BUSCAR POR ID
    // GET /setores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(
            @PathVariable Integer id) {

        Optional<Setor> setorOptional =
                setorService.buscarPorId(id);

        if (setorOptional.isPresent()) {
            return ResponseEntity.ok(setorOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    // ATUALIZAR
    // PUT /setores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Setor novosDados) {

        Optional<Setor> setorOptional =
                setorService.atualizar(id, novosDados);

        if (setorOptional.isPresent()) {
            return ResponseEntity.ok(setorOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    // DELETAR
    // DELETE /setores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        boolean deletado = setorService.deletar(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
