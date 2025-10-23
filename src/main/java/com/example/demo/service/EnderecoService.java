package com.example.demo.service;

import com.example.demo.domain.Endereco;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public List<Endereco> listarTodos() {
        return repository.findAll();
    }

    public Optional<Endereco> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Endereco salvar(Endereco Endereco) {
        return repository.save(Endereco);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
