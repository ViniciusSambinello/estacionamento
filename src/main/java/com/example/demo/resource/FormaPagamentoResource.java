package com.example.demo.resource;



import com.example.demo.domain.FormaPagamento;
import com.example.demo.service.FormaPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formaspagamento")
public class FormaPagamentoResource {

    private final FormaPagamentoService service;

    public FormaPagamentoResource(FormaPagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> salvar(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento salvo = service.salvar(formaPagamento);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Integer id,
                                                    @RequestBody FormaPagamento formaPagamento) {
        return service.buscarPorId(id)
                .map(existente -> {
                    formaPagamento.setId(existente.getId());
                    FormaPagamento atualizado = service.salvar(formaPagamento);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(existente -> {
                    service.deletar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
