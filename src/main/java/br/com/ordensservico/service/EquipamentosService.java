package br.com.ordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.model.Equipamentos;
import br.com.ordensservico.repository.EquipamentosRepository;

@Service
public class EquipamentosService {

    private final EquipamentosRepository equipamentosRepository;

    public EquipamentosService(
            EquipamentosRepository equipamentosRepository) {

        this.equipamentosRepository = equipamentosRepository;
    }


    // ==========================
    // CADASTRAR
    // ==========================

    public Equipamentos cadastrar(
            Equipamentos equipamento) {

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

    public Optional<Equipamentos> buscarPorId(
            Integer id) {

        return equipamentosRepository.findById(id);
    }


    // ==========================
    // ATUALIZAR
    // ==========================

    public Optional<Equipamentos> atualizar(
            Integer id,
            Equipamentos novosDados) {

        Optional<Equipamentos> encontrado =
                equipamentosRepository.findById(id);

        if (encontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamentos equipamento = encontrado.get();
        equipamento.setNumeroPatrimonio(novosDados.getNumeroPatrimonio());
        equipamento.setNome(novosDados.getNome());

        Equipamentos salvo =
                equipamentosRepository.save(equipamento);

        return Optional.of(salvo);
    }


    // ==========================
    // DELETAR
    // ==========================

    public boolean deletar(
            Integer id) {

        if (!equipamentosRepository.existsById(id)) {
            return false;
        }

        equipamentosRepository.deleteById(id);

        return true;
    }

}
    
