package br.com.ordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.model.Setor;
import br.com.ordensservico.repository.SetorRepository;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }


    // CADASTRAR
    public Setor cadastrar(Setor setor) {
        return setorRepository.save(setor);
    }


    // LISTAR
    public List<Setor> listar() {
        return setorRepository.findAll();
    }


    // BUSCAR POR ID
    public Optional<Setor> buscarPorId(Integer id) {
        return setorRepository.findById(id);
    }


    // ATUALIZAR
    public Optional<Setor> atualizar(
            Integer id,
            Setor novosDados) {

        Optional<Setor> setorEncontrado =
                setorRepository.findById(id);

        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Setor setor = setorEncontrado.get();

        setor.setNome(novosDados.getNome());

        Setor setorAtualizado =
                setorRepository.save(setor);

        return Optional.of(setorAtualizado);
    }


    // DELETAR
    public boolean deletar(Integer id) {

        if (!setorRepository.existsById(id)) {
            return false;
        }

        setorRepository.deleteById(id);

        return true;
    }
}