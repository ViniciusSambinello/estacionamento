package com.example.demo.resource;

import com.example.demo.domain.Endereco;
import com.example.demo.domain.Endereco;
import com.example.demo.query.EnderecoFilter;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    private final EnderecoService service;

    @Autowired
    private EnderecoRepository repo;

    public EnderecoResource(EnderecoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtro")
    public Page<Endereco> findAllPag(EnderecoFilter enderecoFilter, Pageable pageable){
        Page<Endereco> filtro = repo.filtrar(enderecoFilter, pageable);
        return filtro;
    }

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
        Endereco novoEndereco = service.salvar(endereco);
        return ResponseEntity.ok(novoEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Integer id, @RequestBody Endereco endereco) {
        return service.buscarPorId(id)
                .map(existente -> {
                    endereco.setId(id);
                    Endereco atualizado = service.salvar(endereco);
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
