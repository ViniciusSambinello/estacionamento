package com.example.demo.repository.impl;

import com.example.demo.domain.Endereco;
import com.example.demo.query.EnderecoFilter;
import com.example.demo.query.EnderecoRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class EnderecoRepositoryImpl implements EnderecoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Page<Endereco> filtrar(EnderecoFilter enderecoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Endereco> criteria = builder.createQuery(Endereco.class);
        Root<Endereco> root = criteria.from(Endereco.class);
        criteria.distinct(true);
        List<Order> orderList = new ArrayList<>();
        orderList.add(builder.desc(root.get("id")));
        criteria.orderBy(orderList);

        Predicate[] predicates = criarRestricoes(enderecoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Endereco> query = manager.createQuery(criteria);
        query.setMaxResults(10);
        adicionarRestricoesDePaginacao(query, pageable);

        List<Endereco> resultList = query.getResultList();

        return new PageImpl<>(resultList, pageable, 0);

    }

    private Predicate[] criarRestricoes(EnderecoFilter enderecoFilter, CriteriaBuilder builder, Root<Endereco> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (enderecoFilter.getId() != null) {
            predicates.add(builder.equal(builder.lower(root.get("id")), enderecoFilter.getId()));
        }

        if (enderecoFilter.getNumero() != null) {
            predicates.add(builder.equal(builder.lower(root.get("numero")), enderecoFilter.getNumero()));
        }

        if (enderecoFilter.getComplemento() != null) {
            predicates.add(builder.like(builder.lower(root.get("complemento")), "%" + enderecoFilter.getComplemento() + "%"));
        }

        if (enderecoFilter.getLogradouro() != null) {
            predicates.add(builder.like(builder.lower(root.get("logradouro")), "%" + enderecoFilter.getLogradouro() + "%"));
        }

        if (enderecoFilter.getBairro() != null) {
            predicates.add(builder.like(builder.lower(root.get("bairro")), "%" + enderecoFilter.getBairro() + "%"));
        }

        if (enderecoFilter.getCep() != null) {
            predicates.add(builder.like(builder.lower(root.get("cep")), "%" + enderecoFilter.getCep() + "%"));
        }

        if (enderecoFilter.getCidade() != null) {
            predicates.add(builder.like(builder.lower(root.get("cidade")), "%" + enderecoFilter.getCidade() + "%"));
        }

        if (enderecoFilter.getUf() != null) {
            predicates.add(builder.like(builder.lower(root.get("uf")), "%" + enderecoFilter.getUf() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Endereco> query, Pageable pageable) {

        int paginaAtual = pageable.getPageNumber();

        int totalRegistrosPorPagina = pageable.getPageSize();

        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);

    }

}