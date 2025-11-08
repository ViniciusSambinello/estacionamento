-- V2__create_auth_tables.sql

-- Criação da tabela de papéis (roles)
CREATE TABLE tb_roles (
    role_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (role_id)
);

-- Criação da tabela de usuários
CREATE TABLE tb_users (
    user_id BINARY(16) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

-- Criação da tabela intermediária de relacionamento N:N entre usuários e papéis
CREATE TABLE tb_users_roles (
    user_id BINARY(16) NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES tb_roles(role_id) ON DELETE CASCADE
);
