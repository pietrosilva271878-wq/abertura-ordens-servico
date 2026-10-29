package br.com.ordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ordensservico.model.Setor;
import br.com.ordensservico.service.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {
    
    private final SetorService SetorService;

    public SetorController(SetorService SetorService) {
       this.SetorService = SetorService;
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrar(
             @RequestBody Setor Setor) {
        
        Setor SetorCadastrado = SetorService.cadastrar(Setor);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(SetorCadastrado);
        
        
        }
    
    @GetMapping
    public ResponseEntity<List<Setor>> listar() {
        List<Setor> Setors = SetorService.listar();

        return ResponseEntity.ok(Setors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(@PathVariable Integer id){
        Optional<Setor> SetorOptional = SetorService.buscarPorId(id);
        
        if (SetorOptional.isPresent()){
            return ResponseEntity.ok(SetorOptional.get());

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(
        @PathVariable Integer id,
        @RequestBody Setor novosDados){
            Optional<Setor> SetorOptional = SetorService.atualizar(id, novosDados);

            if (SetorOptional.isPresent()){
                return ResponseEntity.ok(SetorOptional.get());
            }
            return ResponseEntity.notFound().build();
        }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id ){
        boolean deletado = SetorService.deletar(id);

        if (deletado){
            return ResponseEntity.noContent().build();

        }   
        return ResponseEntity.notFound().build();
    }
    
}
