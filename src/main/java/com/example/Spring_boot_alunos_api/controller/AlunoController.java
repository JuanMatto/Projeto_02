package com.example.alunos.controller;

import com.example.alunos.model.Aluno;
import com.example.alunos.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        return alunoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        return alunoService.findById(id)
                .map(existingAluno -> {
                    existingAluno.setNome(aluno.getNome());
                    existingAluno.setEmail(aluno.getEmail());
                    existingAluno.setIdade(aluno.getIdade());
                    alunoService.save(existingAluno);
                    return ResponseEntity.ok(existingAluno);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        if (alunoService.findById(id).isPresent()) {
            alunoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
