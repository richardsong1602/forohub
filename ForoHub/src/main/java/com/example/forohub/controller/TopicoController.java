package com.example.forohub.controller;

import com.example.forohub.model.Topico;
import com.example.forohub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    /**
     * Crea un nuevo tópico.
     *
     * @param topico el tópico a crear
     * @return el tópico creado
     */
    @PostMapping
    public ResponseEntity<Topico> crearTopico(@RequestBody Topico topico) {
        if (topico == null) {
            throw new NullPointerException("El tópico no puede ser nulo");
        }
        Topico nuevoTopico = topicoService.crearTopico(topico);
        return new ResponseEntity<>(nuevoTopico, HttpStatus.CREATED);
    }

    /**
     * Obtiene todos los tópicos.
     *
     * @return lista de tópicos
     */
    @GetMapping
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoService.obtenerTodosLosTopicos();
    }

    /**
     * Obtiene un tópico por ID.
     *
     * @param id el ID del tópico
     * @return el tópico encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.obtenerTopicoPorId(id);
        if (topico == null) {
            throw new TopicoNotFoundException("No se encontró el tópico con ID " + id);
        }
        return new ResponseEntity<>(topico, HttpStatus.OK);
    }

    /**
     * Actualiza un tópico.
     *
     * @param id el ID del tópico
     * @param topico el tópico actualizado
     * @return el tópico actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody Topico topico) {
        if (topico == null) {
            throw new NullPointerException("El tópico no puede ser nulo");
        }
        Topico topicoActualizado = topicoService.actualizarTopico(id, topico);
        return new ResponseEntity<>(topicoActualizado, HttpStatus.OK);
    }

    /**
     * Elimina un tópico.
     *
     * @param id el ID del tópico
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

class TopicoNotFoundException extends RuntimeException {
    public TopicoNotFoundException(String message) {
        super(message);
    }
}
