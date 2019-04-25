CREATE TABLE produto (
             id INT NOT NULL AUTO_INCREMENT,
             nome VARCHAR(255) NOT NULL,
             preco_atual DECIMAL(10,2) NULL,
             ativo BOOLEAN NOT NULL,
             PRIMARY KEY (id)
) engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO produto (nome, preco_atual, ativo) VALUES ('Computador All-in-one', '2199.90', true);
INSERT INTO produto (nome, preco_atual, ativo) VALUES ('Notebook Samsung Core i7', '2099.50', true);
INSERT INTO produto (nome, preco_atual, ativo) VALUES ('HD SSD 500gb', '849.00', true);