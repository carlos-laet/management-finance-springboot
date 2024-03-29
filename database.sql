CREATE DATABASE management-finance

CREATE SCHEMA finances;

CREATE TABLE finances.usuario
(
    id bigserial NOT NULL PRIMARY KEY,
    nome character varying(150),
    email character varying(100),
    senha character varying(20),
    data_cadastro date default now()
);

CREATE TABLE finances.lancamento
(
    id bigserial NOT NULL PRIMARY KEY ,
    descricao character varying(100) NOT NULL,
    mes integer NOT NULL,
    ano integer NOT NULL,
    valor numeric(16,2),
    tipo character varying(20) CHECK ( tipo in ('RECEITA','DESPESA') ) NOT NULL,
    status character varying(20) CHECK ( status in ('PENDENTE', 'CANCELADO', 'EFETIVADO') ) NOT NULL,
    id_usuario bigint REFERENCES finances.usuario (id) NOT NULL,
    data_cadastro date default now()
);