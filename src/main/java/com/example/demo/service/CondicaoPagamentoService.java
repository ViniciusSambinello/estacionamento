package com.example.demo.service;

import com.example.demo.domain.CondicaoPagamento;
import com.example.demo.repository.CondicaoPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondicaoPagamentoService {

    private final CondicaoPagamentoRepository repository;

    public CondicaoPagamentoService(CondicaoPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<CondicaoPagamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<CondicaoPagamento> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public CondicaoPagamento salvar(CondicaoPagamento condicaoPagamento) {
        return repository.save(condicaoPagamento);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
