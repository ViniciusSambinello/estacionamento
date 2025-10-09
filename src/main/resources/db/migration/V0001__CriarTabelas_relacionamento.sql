CREATE TABLE `caixa` ( 
  `cliente_id` INT NULL DEFAULT NULL ,
  `cond_pagamento_id` INT NULL DEFAULT NULL ,
  `controle_fluxo_id` INT NULL DEFAULT NULL ,
  `forma_pagamento_id` INT NULL DEFAULT NULL ,
  `id` INT AUTO_INCREMENT NOT NULL,
  `valor` DECIMAL(38,2) NULL DEFAULT NULL ,
  `dia` DATETIME NULL DEFAULT NULL ,
  `tipo` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`),
  CONSTRAINT `UK_kgt4y4oeetc5ev9ovy10oftfa` UNIQUE (`controle_fluxo_id`)
)
ENGINE = InnoDB;
CREATE TABLE `cliente` ( 
  `endereco_id` INT NULL DEFAULT NULL ,
  `id` INT AUTO_INCREMENT NOT NULL,
  `limite_de_compra` DECIMAL(10,2) NULL DEFAULT NULL ,
  `data_nasc` DATETIME NULL DEFAULT NULL ,
  `cpf` VARCHAR(255) NULL DEFAULT NULL ,
  `email` VARCHAR(255) NULL DEFAULT NULL ,
  `nome` VARCHAR(255) NULL DEFAULT NULL ,
  `plano` VARCHAR(255) NULL DEFAULT NULL ,
  `telefone` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`),
  CONSTRAINT `UK_7v21uy9djyl7hh9464kkjsjg0` UNIQUE (`endereco_id`)
)
ENGINE = InnoDB;
CREATE TABLE `condicaopagamento` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `conditens` ( 
  `condicao_id` INT NULL DEFAULT NULL ,
  `id` INT AUTO_INCREMENT NOT NULL,
  `parcela` INT NULL DEFAULT NULL ,
  `percentual` DECIMAL(38,2) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `controlefluxo` ( 
  `cliente_id` INT NULL DEFAULT NULL ,
  `id` INT AUTO_INCREMENT NOT NULL,
  `status` BIT NULL DEFAULT NULL ,
  `valor` DECIMAL(38,2) NULL DEFAULT NULL ,
  `veiculo_id` INT NULL DEFAULT NULL ,
  `anomesdia_hora_entrada` DATETIME NULL DEFAULT NULL ,
  `anomesdia_hora_saida` DATETIME NULL DEFAULT NULL ,
  `tipo` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`),
  CONSTRAINT `UK_s2yypp3wmdfs283fg7my4gwhe` UNIQUE (`veiculo_id`),
  CONSTRAINT `UK_rlhr06p82atysuvl89exln4c7` UNIQUE (`cliente_id`)
)
ENGINE = InnoDB;
CREATE TABLE `endereco` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `bairro` VARCHAR(255) NULL DEFAULT NULL ,
  `cep` VARCHAR(255) NULL DEFAULT NULL ,
  `cidade` VARCHAR(255) NULL DEFAULT NULL ,
  `complemento` VARCHAR(255) NULL DEFAULT NULL ,
  `logradouro` VARCHAR(255) NULL DEFAULT NULL ,
  `numero` VARCHAR(255) NULL DEFAULT NULL ,
  `uf` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `financeiro` ( 
  `cliente_id` INT NULL DEFAULT NULL ,
  `cond_pagamento_id` INT NULL DEFAULT NULL ,
  `forma_pagamento_id` INT NULL DEFAULT NULL ,
  `id` INT AUTO_INCREMENT NOT NULL,
  `valor_desconto` DECIMAL(10,2) NULL DEFAULT NULL ,
  `valor_despesas` DECIMAL(10,2) NULL DEFAULT NULL ,
  `valor_juros` DECIMAL(10,2) NULL DEFAULT NULL ,
  `valor_original` DECIMAL(10,2) NULL DEFAULT NULL ,
  `data_emissao` DATETIME NULL DEFAULT NULL ,
  `data_pagamento` DATETIME NULL DEFAULT NULL ,
  `data_vencimento` DATETIME NULL DEFAULT NULL ,
  `status` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `formapagamento` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `tabelapreco` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `preco` DECIMAL(10,2) NULL DEFAULT NULL ,
  `tipo_contrato` VARCHAR(255) NULL DEFAULT NULL ,
  `tipo_veiculo` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `usuario` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL ,
  `nome` VARCHAR(255) NULL DEFAULT NULL ,
  `senha` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `veiculo` ( 
  `cliente_id` INT NULL DEFAULT NULL ,
  `id` INT AUTO_INCREMENT NOT NULL,
  `numero_vagas` INT NULL DEFAULT NULL ,
  `cor` VARCHAR(255) NULL DEFAULT NULL ,
  `marca` VARCHAR(255) NULL DEFAULT NULL ,
  `modelo` VARCHAR(255) NULL DEFAULT NULL ,
  `placa` VARCHAR(255) NOT NULL,
  `tipo` VARCHAR(255) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
ALTER TABLE `caixa` ADD CONSTRAINT `FKae1encxm5glqu88jgufmj30oa` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `formapagamento` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `caixa` ADD CONSTRAINT `FK4ag8euacnps7pvacld39su12x` FOREIGN KEY (`controle_fluxo_id`) REFERENCES `controlefluxo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `caixa` ADD CONSTRAINT `FKl2wir10nlcqiivsopm50848iw` FOREIGN KEY (`cond_pagamento_id`) REFERENCES `condicaopagamento` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `caixa` ADD CONSTRAINT `FK8gcu61fcm9rcgvpxu07ta1dwt` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `cliente` ADD CONSTRAINT `FK64nr9yt889by5lufr1boo5i4s` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `conditens` ADD CONSTRAINT `FKg1f0bqr3syr9rv6nb7w6mfvq6` FOREIGN KEY (`condicao_id`) REFERENCES `condicaopagamento` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `controlefluxo` ADD CONSTRAINT `FKoy4fpamnyhng7wm6udf5ewp14` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `controlefluxo` ADD CONSTRAINT `FKiyg4ij0lf29rvm36hok8dkxv7` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `financeiro` ADD CONSTRAINT `FKhnu2rwnx514wpeumps8b4fawb` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `financeiro` ADD CONSTRAINT `FKbt6hj0ikykxhn6d87qo8rf44a` FOREIGN KEY (`cond_pagamento_id`) REFERENCES `condicaopagamento` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `financeiro` ADD CONSTRAINT `FKe7kll5a5xkud74jhe149lku8f` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `formapagamento` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `veiculo` ADD CONSTRAINT `FKny7f0cx7lnl40poaqcijjxyao` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
