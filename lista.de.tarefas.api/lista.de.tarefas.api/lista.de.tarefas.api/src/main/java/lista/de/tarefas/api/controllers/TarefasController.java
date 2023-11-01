package lista.de.tarefas.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lista.de.tarefas.api.database.TarefasDAO;
import lista.de.tarefas.api.models.Tarefa;

@RestController
@CrossOrigin
@RequestMapping("/tarefas")
public class TarefasController {

    @GetMapping("/listarTarefas")
    public List<Tarefa> listarTarefas() {
        var tarefasDao = new TarefasDAO();

        return tarefasDao.listarTarefas();
    }

    @PostMapping("/adicionarTarefa")
    public ResponseEntity<HttpStatus> adicionarTarefa(@RequestBody String descricao) {
        var tarefasDao = new TarefasDAO();
        tarefasDao.adicionarTarefa(descricao);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("/atualizarTarefa")
    public ResponseEntity<HttpStatus> atualizarTarefa(@RequestBody Tarefa tarefa) {
        var tarefasDao = new TarefasDAO();
        tarefasDao.atualizarTarefa(tarefa);

        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deletarTarefa/{id}")
    public ResponseEntity<HttpStatus> deletarTarefa(@PathVariable int id) {
        var tarefasDao = new TarefasDAO();
        tarefasDao.deletarTarefa(id);

        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}