package com.taskmaster.controller;

import com.taskmaster.entity.Tarefa;
import com.taskmaster.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> consultarTarefa(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.consultarTarefa(id);

        if (tarefa != null) {
            return new ResponseEntity<>(tarefa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.adicionarTarefa(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        tarefa.setId(id);
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(tarefa);

        if (tarefaAtualizada != null) {
            return new ResponseEntity<>(tarefaAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
        boolean removido = tarefaService.removerTarefa(id);

        if (removido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
