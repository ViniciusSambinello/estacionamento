package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")  // nome da tabela no banco
@Data                     // gera getters, setters, toString, equals e hashCode
@NoArgsConstructor         // gera construtor sem argumentos
@AllArgsConstructor        // gera construtor com todos os argumentos
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private Date dataNasc;
    @OneToOne
    private Endereco end;
    private String email;
    @Column(precision = 10, scale = 2)
    private BigDecimal limiteDeCompra;
    
    
    
    
    

}