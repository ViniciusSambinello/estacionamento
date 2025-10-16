package com.example.demo.query;

import com.example.demo.domain.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoRepositoryQuery {

    Page<Endereco> filtrar(EnderecoFilter tipocontatofilter, Pageable pageable);
}
