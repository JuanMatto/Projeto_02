package com.example.alunos.service;

import com.example.alunos.model.Aluno;
import com.example.alunos.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }
}
