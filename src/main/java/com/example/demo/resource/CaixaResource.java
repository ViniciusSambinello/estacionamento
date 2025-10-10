package com.example.demo.resource;

import com.example.demo.domain.Caixa;
import com.example.demo.domain.Cliente;
import com.example.demo.service.CaixaService;
import com.example.demo.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixa")
public class CaixaResource {

    private final CaixaService service;

    public CaixaResource(CaixaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Caixa>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Caixa> salvar(@RequestBody Caixa caixa) {
        Caixa novoCaixa = service.salvar(caixa);
        return ResponseEntity.ok(novoCaixa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caixa> atualizar(@PathVariable Integer id, @RequestBody Caixa caixa) {
        return service.buscarPorId(id)
                .map(existente -> {
                    caixa.setId(id);
                    Caixa atualizado = service.salvar(caixa);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
