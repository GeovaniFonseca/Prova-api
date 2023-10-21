package com.taskmaster.service;

import com.taskmaster.entity.Tarefa;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TarefaService {

    private List<Tarefa> tarefas;
    private final AtomicLong nextId = new AtomicLong(1);

    public TarefaService() {
        tarefas = new ArrayList<>();
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        tarefa.setId(nextId.getAndIncrement());
        tarefas.add(tarefa);
        return tarefa;
    }

    public Tarefa consultarTarefa(Long id) {
        Optional<Tarefa> tarefaOptional = tarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .findFirst();

        return tarefaOptional.orElse(null);
    }

    public Tarefa atualizarTarefa(Tarefa tarefa) {
        Optional<Tarefa> tarefaOptional = tarefas.stream()
                .filter(terefa -> tarefa.getId().equals(tarefa.getId()))
                .findFirst();

        if (tarefaOptional.isPresent()) {
            Tarefa tarefaExistente = tarefaOptional.get();
            tarefaExistente.setDescricao(tarefa.getDescricao());
            tarefaExistente.setStatus(tarefa.getStatus());
            return tarefaExistente;
        } else {
            return null;
        }
    }

    public boolean removerTarefa(Long id) {
        Optional<Tarefa> tarefaOptional = tarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .findFirst();

        if (tarefaOptional.isPresent()) {
            tarefas.remove(tarefaOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
