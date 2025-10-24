package com.example.demo.specification;

import com.example.demo.domain.Endereco;
import com.example.demo.repository.filters.EnderecoFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class EnderecoSpecification {

    public static Specification<Endereco> withFilters(EnderecoFilter filter) {
        return (Root<Endereco> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.getId() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("id")), "%" + filter.getId() + "%"));
            }

            if (filter.getNumero() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("numero")), "%" + filter.getNumero() + "%"));
            }

            if (filter.getComplemento() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("complemento")), "%" + filter.getComplemento() + "%"));
            }

            if (filter.getLogradouro() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("logradouro")), "%" + filter.getLogradouro() + "%"));
            }

            if (filter.getBairro() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("bairro")), "%" + filter.getBairro() + "%"));
            }

            if (filter.getCep() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("cep")), "%" + filter.getCep() + "%"));
            }

            if (filter.getCidade() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("cidade")), "%" + filter.getCidade() + "%"));
            }

            if (filter.getUf() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("uf")), "%" + filter.getUf() + "%"));
            }

            return predicate;
        };
    }
}
