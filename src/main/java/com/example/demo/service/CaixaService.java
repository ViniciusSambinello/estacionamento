package com.example.demo.service;

import com.example.demo.domain.Caixa;
import com.example.demo.repository.CaixaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    private final CaixaRepository repository;

    public CaixaService(CaixaRepository repository) {
        this.repository = repository;
    }

    public List<Caixa> listarTodos() {
        return repository.findAll();
    }

    public Optional<Caixa> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Caixa salvar(Caixa caixa) {
        return repository.save(caixa);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
