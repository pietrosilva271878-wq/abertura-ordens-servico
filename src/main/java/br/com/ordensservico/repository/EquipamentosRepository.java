
package br.com.ordensservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ordensservico.model.Equipamentos;

public interface EquipamentosRepository
        extends JpaRepository<Equipamentos, Integer> {
}
