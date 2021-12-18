--liquibase formatted sql

--changeset mbocek:1
CREATE TABLE address
(
    id SERIAL NOT NULL,
    city VARCHAR(255),
    street VARCHAR(255),
    zip VARCHAR(10),
    CONSTRAINT pk_address PRIMARY KEY (id)
);
