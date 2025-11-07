package com.example.demo.auth.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleid;

    private String name;

    @RequiredArgsConstructor
    @Getter
    public enum Values {

        ADMIN(1L),
        BASIC(2L);

        final long roleid;
    }
}
