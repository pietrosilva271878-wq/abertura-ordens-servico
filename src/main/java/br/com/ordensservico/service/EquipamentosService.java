package br.com.ordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.model.Equipamentos;
import br.com.ordensservico.dto.EquipamentosDTO;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import br.com.ordensservico.model.Setor;
import br.com.ordensservico.repository.EquipamentosRepository;
import br.com.ordensservico.repository.SetorRepository;

@Service
public class EquipamentosService {

    private final EquipamentosRepository equipamentosRepository;
    private final SetorRepository setorRepository;

    public EquipamentosService(
            EquipamentosRepository equipamentosRepository,
            SetorRepository setorRepository) {

        this.equipamentosRepository = equipamentosRepository;
        this.setorRepository = setorRepository;
    }

    // ==========================
    // CADASTRAR
    // ==========================

    public Equipamentos cadastrar(EquipamentosDTO dados) {

        Integer setorId = dados.getSetorId();

        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));

        Equipamentos equipamento = new Equipamentos();
        equipamento.setNome(dados.getNome());
        equipamento.setNumeroPatrimonio(dados.getNumeroPatrimonio());
        equipamento.setSetor(setor);

        return equipamentosRepository.save(equipamento);
    }

    // ==========================
    // LISTAR
    // ==========================

    public List<Equipamentos> listar() {

        return equipamentosRepository.findAll();
    }

    // ==========================
    // BUSCAR POR ID
    // ==========================

    public Optional<Equipamentos> buscarPorId(Integer id) {

        return equipamentosRepository.findById(id);
    }

    // ==========================
    // ATUALIZAR
    // ==========================

    public Optional<Equipamentos> atualizar(
            Integer id,
            EquipamentosDTO novosDados) {

        Optional<Equipamentos> encontrado =
                equipamentosRepository.findById(id);

        if (encontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamentos equipamento = encontrado.get();

        // Atualiza nome
        equipamento.setNome(novosDados.getNome());

        // Atualiza patrimônio
        equipamento.setNumeroPatrimonio(
                novosDados.getNumeroPatrimonio()
        );

        // Busca o setor informado
        Integer setorId = novosDados.getSetorId();

        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));

        // Atualiza o setor
        equipamento.setSetor(setor);

        Equipamentos salvo =
                equipamentosRepository.save(equipamento);

        return Optional.of(salvo);
    }

    // ==========================
    // DELETAR
    // ==========================

    public boolean deletar(Integer id) {

        if (!equipamentosRepository.existsById(id)) {
            return false;
        }

        equipamentosRepository.deleteById(id);

        return true;
    }
}
    
