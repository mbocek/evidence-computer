--liquibase formatted sql

--changeset mbocek:1
CREATE TABLE computer
(
    id SERIAL NOT NULL,
    domain VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    purchase_date DATE NOT NULL,
    vendor VARCHAR(255) NOT NULL,
    locality_id BIGINT NOT NULL,
    CONSTRAINT pk_computer PRIMARY KEY (id),
    CONSTRAINT fk_locality FOREIGN KEY (locality_id)
        REFERENCES address (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
