package com.example.forohub.repository;

import com.example.forohub.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    List<Respuesta> findByTopicoId(Long topicoId);

    List<Respuesta> findByTextoContaining(String texto);

    List<Respuesta> findByUsuarioId(Long usuarioId);

    List<Respuesta> findByTopicoIdAndUsuarioId(Long topicoId, Long usuarioId);

    Long countByTopicoId(Long topicoId);

}