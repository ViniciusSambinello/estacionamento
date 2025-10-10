package com.example.demo.repository;

import com.example.demo.domain.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaixaRepository extends JpaRepository<Caixa, Integer> {
}
