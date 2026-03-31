package com.bichofull.backend.repository;

import com.bichofull.backend.entity.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ApostaRepository extends JpaRepository<Aposta, Long> {
    
    List<Aposta> findByUsuarioId(Long usuarioId);
}