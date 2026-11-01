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

    public Setor cadastrar(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listar() {
        return setorRepository.findAll();
    }

    public Optional<Setor> buscarPorId(Integer id) {
        return setorRepository.findById(id);
    }

    public Optional<Setor> atualizar(
            Integer id,
            Setor novosDados) {

        Optional<Setor> encontrado =
                setorRepository.findById(id);

        if (encontrado.isEmpty()) {
            return Optional.empty();
        }

        Setor setor = encontrado.get();

        setor.setNome(novosDados.getNome());

        Setor salvo = setorRepository.save(setor);

        return Optional.of(salvo);
    }

    public boolean deletar(Integer id) {

        if (!setorRepository.existsById(id)) {
            return false;
        }

        setorRepository.deleteById(id);

        return true;
    }
}