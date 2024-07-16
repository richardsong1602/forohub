package com.example.forohub.service;

import com.example.forohub.model.Topico;
import com.example.forohub.repository.TopicoRepository;
import com.example.forohub.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con ID: " + id));
    }

    public Topico actualizarTopico(Long id, Topico detallesTopico) {
        Topico topico = obtenerTopicoPorId(id);

        if (detallesTopico.getTitulo() != null && !detallesTopico.getTitulo().isEmpty()) {
            topico.setTitulo(detallesTopico.getTitulo());
        }

        if (detallesTopico.getMensaje() != null && !detallesTopico.getMensaje().isEmpty()) {
            topico.setMensaje(detallesTopico.getMensaje());
        }

        return topicoRepository.save(topico);
    }

    public void eliminarTopico(Long id) {
        Topico topico = obtenerTopicoPorId(id);
        topicoRepository.delete(topico);
    }
}
