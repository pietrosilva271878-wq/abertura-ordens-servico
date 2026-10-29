package br.com.ordensservico.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ordensservico.model.Equipamentos;

@Repository
public interface EquipamentosRepository
        extends JpaRepository<Equipamentos, Integer> {
        }