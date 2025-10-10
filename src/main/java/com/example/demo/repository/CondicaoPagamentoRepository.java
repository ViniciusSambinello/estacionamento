package com.example.demo.repository;

import com.example.demo.domain.CondicaoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicaoPagamentoRepository extends JpaRepository<CondicaoPagamento, Integer> {
}
