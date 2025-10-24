package com.example.demo.repository.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoFilter {

    private Integer id;
    private String numero;
    private String complemento;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
}