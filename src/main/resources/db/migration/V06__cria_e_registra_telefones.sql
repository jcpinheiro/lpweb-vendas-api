CREATE TABLE telefones (
  cliente_id INT NOT NULL,
  numero VARCHAR(15) NOT NULL,
  PRIMARY KEY (cliente_id, numero),
  CONSTRAINT cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id)
)engine=InnoDB DEFAULT CHARSET=utf8;


insert into telefones(cliente_id, numero) values (1, "987874564");
insert into telefones(cliente_id, numero) values (1, "988457454");

insert into telefones(cliente_id, numero) values (2, "983457422");