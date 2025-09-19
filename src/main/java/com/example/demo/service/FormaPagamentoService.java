package com.example.demo.service;



import com.example.demo.domain.FormaPagamento;
import com.example.demo.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository repository;

    public FormaPagamentoService(FormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<FormaPagamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<FormaPagamento> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}