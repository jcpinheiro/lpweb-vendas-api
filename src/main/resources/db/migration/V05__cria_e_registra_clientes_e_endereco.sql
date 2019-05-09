CREATE TABLE IF NOT EXISTS cliente (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  email VARCHAR(200) NULL,
  cpf VARCHAR(20) NULL,
  tipo VARCHAR(20) NULL,
  PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS endereco (
  id INT NOT NULL AUTO_INCREMENT,
  logradouro VARCHAR(200) NOT NULL,
  numero VARCHAR(20) NOT NULL,
  complemento VARCHAR(200) NULL,
  cep VARCHAR(9) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  uf CHAR(2) NULL,
  cliente_id INT NOT NULL,
  PRIMARY KEY (id, logradouro, numero),
  INDEX fk_endereco_cliente_idx (cliente_id ASC),
  CONSTRAINT fk_endereco_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES cliente (id)
)engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cliente (nome, email, cpf, tipo) VALUES ('Fulano da Silva', 'fulano@email.com', '73543374559', 'FISICA');
INSERT INTO cliente (nome, email, cpf, tipo) VALUES ('Sicrano de Souza', 'sicrano@email.com', '97737410791', 'FISICA');

INSERT INTO endereco (logradouro, numero, complemento, cep, cidade, uf, cliente_id) VALUES ('Rua da esperança', '20', 'Quadra F', '65000000', 'São Luís', 'MA', 1);
