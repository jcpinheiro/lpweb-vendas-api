CREATE TABLE produto_categoria (
                                   produto_id INT NOT NULL,
                                   categoria_id INT(11) NOT NULL,
                                   PRIMARY KEY (produto_id, categoria_id),
                                   INDEX fk_produto_has_categoria_categoria1_idx (categoria_id ASC),
                                   INDEX fk_produto_has_categoria_produto_idx (produto_id ASC)
) engine=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO produto_categoria (produto_id, categoria_id) VALUES ('1', '1');
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES ('2', '1');
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES ('3', '1');
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES ('3', '2');