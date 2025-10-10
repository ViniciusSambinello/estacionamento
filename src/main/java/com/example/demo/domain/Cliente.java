package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")  // nome da tabela no banco
@Data                     // gera getters, setters, toString, equals e hashCode
@NoArgsConstructor         // gera construtor sem argumentos
@AllArgsConstructor        // gera construtor com todos os argumentos
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private Date dataNasc;
    private String email;
    private String  plano; 
    @OneToOne(cascade = CascadeType.ALL) // <-- Adicione o cascade
    @JoinColumn(name = "endereco_id")    // <-- Define a coluna de FK
    private Endereco endereco;
}