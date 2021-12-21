--liquibase formatted sql

--changeset mbocek:1
CREATE TABLE address
(
    id SERIAL NOT NULL,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);
