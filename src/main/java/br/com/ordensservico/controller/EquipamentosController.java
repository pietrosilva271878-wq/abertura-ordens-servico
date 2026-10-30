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

import br.com.ordensservico.model.Equipamentos;
import br.com.ordensservico.service.EquipamentosService;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentosController {

    private final EquipamentosService equipamentosService;

    public EquipamentosController(EquipamentosService equipamentosService) {
        this.equipamentosService = equipamentosService;
    }

    // ==========================
    // CADASTRAR
    // POST /equipamentos
    // ==========================

    @PostMapping
    public ResponseEntity<Equipamentos> cadastrar(
            @RequestBody Equipamentos equipamento) {

        Equipamentos novoEquipamento =
                equipamentosService.cadastrar(equipamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoEquipamento);
    }

    // ==========================
    // LISTAR
    // GET /equipamentos
    // ==========================

    @GetMapping
    public ResponseEntity<List<Equipamentos>> listar() {

        List<Equipamentos> equipamentos =
                equipamentosService.listar();

        return ResponseEntity.ok(equipamentos);
    }

    // ==========================
    // BUSCAR POR ID
    // GET /equipamentos/{id}
    // ==========================

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

    // ==========================
    // ATUALIZAR
    // PUT /equipamentos/{id}
    // ==========================

    @PutMapping("/{id}")
    public ResponseEntity<Equipamentos> atualizar(
            @PathVariable Integer id,
            @RequestBody Equipamentos equipamento) {

        Optional<Equipamentos> atualizado =
                equipamentosService.atualizar(id, equipamento);

        if (atualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado.get());
    }

    // ==========================
    // DELETAR
    // DELETE /equipamentos/{id}
    // ==========================

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