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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

import br.com.ordensservico.model.Equipamentos;
import br.com.ordensservico.service.EquipamentosService;


@RestController
@RequestMapping("/equipamentos")
public class EquipamentosController {

    private final EquipamentosService EquipamentosService;


    public EquipamentosController(
            EquipamentosService EquipamentosService) {

        this.EquipamentosService =
                EquipamentosService;
    }


    // ==========================
    // CADASTRAR
    // ==========================

    @PostMapping
    public ResponseEntity<Equipamentos> cadastrar(
            @RequestBody Equipamentos Equipamentos) {

        Equipamentos novoEquipamentos =
                EquipamentosService.cadastrar(Equipamentos);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoEquipamentos);
    }


    // ==========================
    // LISTAR
    // ==========================

    @GetMapping
    public ResponseEntity<List<Equipamentos>> listar() {

        return ResponseEntity.ok(
                EquipamentosService.listar()
        );
    }


    // ==========================
    // BUSCAR POR ID
    // ==========================

    @GetMapping("/{id}")
    public ResponseEntity<Equipamentos> buscarPorId(
            @PathVariable Integer id) {


        Optional<Equipamentos> Equipamentos =
                EquipamentosService.buscarPorId(id);


        if (Equipamentos.isEmpty()) {

            return ResponseEntity
                    .notFound()
                    .build();
        }


        return ResponseEntity.ok(
                Equipamentos.get()
        );
    }


    // ==========================
    // ATUALIZAR
    // ==========================

    @PutMapping("/{id}")
    public ResponseEntity<Equipamentos> atualizar(
            @PathVariable Integer id,
            @RequestBody Equipamentos Equipamentos) {


        Optional<Equipamentos> atualizado =
                EquipamentosService.atualizar(
                        id,
                        Equipamentos
                );


        if (atualizado.isEmpty()) {

            return ResponseEntity
                    .notFound()
                    .build();
        }


        return ResponseEntity.ok(
                atualizado.get()
        );
    }


    // ==========================
    // EXCLUIR
    // ==========================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id) {


        boolean deletado =
                EquipamentosService.deletar(id);


        if (!deletado) {

            return ResponseEntity
                    .notFound()
                    .build();
        }


        return ResponseEntity
                .noContent()
                .build();

}
}