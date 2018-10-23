CREATE DATABASE productbd;

CREATE TABLE productbd.product (
  id           BIGINT        NOT NULL AUTO_INCREMENT,
  name         VARCHAR(100)  NOT NULL,
  description    VARCHAR(1000) NULL,
  purchase_price DECIMAL(9,2)  NOT NULL,
  price_sale  DECIMAL(9,2)  NOT NULL,
  amount   INT           NOT NULL DEFAULT 0,
  CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE productbd.category (
  id   INT          NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  CONSTRAINT pk_category PRIMARY KEY (id),
  CONSTRAINT uc_name UNIQUE (name)
);

CREATE TABLE productbd.product_category (
    id_product   BIGINT NOT NULL,
    id_category INT    NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (id_product) REFERENCES productbd.product(id),
    CONSTRAINT fk_category FOREIGN KEY (id_category) REFERENCES productbd.category(id)
);

INSERT INTO productbd.category(name) VALUES ("Categoria Um");
INSERT INTO productbd.category(name) VALUES ("Categoria Dois");
INSERT INTO productbd.category(name) VALUES ("Categoria Três");
INSERT INTO productbd.category(name) VALUES ("Categoria Quatro");
INSERT INTO productbd.category(name) VALUES ("Categoria Cinco");