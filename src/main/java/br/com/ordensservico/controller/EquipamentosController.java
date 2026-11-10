package br.com.ordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ordensservico.model.Equipamentos;
import br.com.ordensservico.dto.EquipamentosDTO;
import br.com.ordensservico.service.EquipamentosService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentosController {

    private final EquipamentosService equipamentosService;

    public EquipamentosController(
            EquipamentosService equipamentosService) {

        this.equipamentosService = equipamentosService;
    }

    @PostMapping
    public ResponseEntity<Equipamentos> cadastrar(
            @Valid @RequestBody EquipamentosDTO equipamento) {

        Equipamentos novoEquipamento =
                equipamentosService.cadastrar(equipamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoEquipamento);
    }

    @GetMapping
    public ResponseEntity<List<Equipamentos>> listar() {

        return ResponseEntity.ok(
                equipamentosService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamentos> buscarPorId(
            @PathVariable Integer id) {

        Optional<Equipamentos> equipamento =
                equipamentosService.buscarPorId(id);

        if (equipamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(equipamento.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamentos> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EquipamentosDTO equipamento) {

        Optional<Equipamentos> atualizado =
                equipamentosService.atualizar(
                        id,
                        equipamento
                );

        if (atualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id) {

        boolean deletado =
                equipamentosService.deletar(id);

        if (!deletado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}