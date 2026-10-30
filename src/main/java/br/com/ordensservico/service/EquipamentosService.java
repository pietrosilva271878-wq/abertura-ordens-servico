package br.com.ordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.model.Equipamentos;
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

    public Equipamentos cadastrar(Equipamentos equipamento) {

        Integer setorId = equipamento.getSetor().getId();

        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() ->
                        new RuntimeException("Setor não encontrado"));

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
            Equipamentos novosDados) {

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
        Integer setorId = novosDados.getSetor().getId();

        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() ->
                        new RuntimeException("Setor não encontrado"));

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
    
