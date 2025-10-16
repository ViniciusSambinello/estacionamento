package com.example.demo.repository.impl;

import com.example.demo.domain.Endereco;
import com.example.demo.query.EnderecoFilter;
import com.example.demo.query.EnderecoRepositoryQuery;
import com.example.demo.repository.EnderecoRepository;
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
        orderList.add(builder.desc(root.get("codigoid")));
        criteria.orderBy(orderList);

        Predicate[] predicates = criarRestricoes(enderecoFilter, builder, root);
        criteria.where(predicates);

        // Criar uma contagem de registros
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<Endereco> countRoot = countCriteria.from(Endereco.class);
        countCriteria.select(builder.count(countRoot));
        Predicate[] countPredicates = criarRestricoes(enderecoFilter, builder, countRoot);
        countCriteria.where(countPredicates);
        TypedQuery<Long> countQuery = manager.createQuery(countCriteria);
        Long totalRegistros = countQuery.getSingleResult(); // Total de registros que satisfazem os predicados

        TypedQuery<Endereco> query = manager.createQuery(criteria);
        query.setMaxResults(10);
        adicionarRestricoesDePaginacao(query, pageable);

        List<Endereco> resultList = query.getResultList();

        return new PageImpl<>(resultList, pageable, totalRegistros);

    }

    private Predicate[] criarRestricoes(EnderecoFilter enderecoFilter, CriteriaBuilder builder, Root<Endereco> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (enderecoFilter.getId() != null) {
            try {
                Integer valor = Integer.parseInt(enderecoFilter.getId());
                predicates.add(builder.equal(builder.lower(root.get("id")), valor));
            } catch (Exception e) {
                predicates.add(builder.equal(builder.lower(root.get("id")), 0));
            }

        }

        if (enderecoFilter.getCodigoid() != null) {
            try {
                Integer valor = Integer.parseInt(enderecoFilter.getCodigoid());
                predicates.add(builder.equal(builder.lower(root.get("codigoid")), valor));
            } catch (Exception e) {
                predicates.add(builder.equal(builder.lower(root.get("codigoid")), 0));
            }

        }

        if (enderecoFilter.getTenant() != null) {
            predicates.add(builder.equal(builder.lower(root.get("tenant").get("id")), enderecoFilter.getTenant()));
        }

        if (enderecoFilter.getDescricao() != null) {
            predicates.add(builder.like(builder.lower(root.get("descricao")), "%" + enderecoFilter.getDescricao() + "%"));
        }

        if (enderecoFilter.getDatausucriacaode() != null) {
            Instant instant = enderecoFilter.getDatausucriacaode().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
            predicates.add(builder.greaterThanOrEqualTo(root.get("datausucriacao"), offsetDateTime));
        }
        if (enderecoFilter.getDatausucriacaoate() != null) {
            Instant instant = enderecoFilter.getDatausucriacaoate().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
            predicates.add(builder.lessThanOrEqualTo(root.get("datausucriacao"), offsetDateTime.plusDays(1)));
        }
        if (enderecoFilter.getUsucriacao() != null) {
            predicates
                    .add(builder.like(builder.lower(root.get("usucriacao")), "%" + enderecoFilter.getUsucriacao() + "%"));
        }
        if (enderecoFilter.getDatagravacaode() != null) {

            Instant instant = enderecoFilter.getDatagravacaode().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);

            predicates.add(builder.greaterThanOrEqualTo(root.get("datagravacao"), offsetDateTime));
        }

        if (enderecoFilter.getDatagravacaoate() != null) {

            Instant instant = enderecoFilter.getDatagravacaoate().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
            predicates.add(builder.lessThanOrEqualTo(root.get("datagravacao"), offsetDateTime.plusDays(1)));

        }

        if (enderecoFilter.getUsualteracao() != null) {
            predicates.add(
                    builder.like(builder.lower(root.get("usualteracao")), "%" + enderecoFilter.getUsualteracao() + "%"));
        }

        if (enderecoFilter.getStatus().equals("Ativos")) {
            predicates.add(builder.equal(builder.lower(root.get("status")), true));
        } else {
            predicates.add(builder.equal(builder.lower(root.get("status")), false));
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