package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conditens")  // nome da tabela no banco
@Data                     // gera getters, setters, toString, equals e hashCode
@NoArgsConstructor         // gera construtor sem argumentos
@AllArgsConstructor        // gera construtor com todos os argumentos
public class CondItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal percentual;
    @ManyToOne
    private CondicaoPagamento condicao;
    private Integer parcela;
    

}
