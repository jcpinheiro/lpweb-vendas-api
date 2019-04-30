ALTER TABLE categoria
    ADD COLUMN descricao VARCHAR(255) NULL AFTER nome,
    ADD COLUMN momento_criacao DATETIME NULL AFTER descricao;