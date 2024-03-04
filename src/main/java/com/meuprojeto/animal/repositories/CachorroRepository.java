package com.meuprojeto.animal.repositories;

import com.meuprojeto.animal.entities.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CachorroRepository extends JpaRepository<Cachorro, Long> {
}
