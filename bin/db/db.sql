--- ECOtoll database schema
--- Review 0 - Samuel Finocchio 22/05/19

CREATE TABLE IF NOT EXISTS veicolo (
      -- Chiave primaria
      id BIGINT AUTO_INCREMENT,

      -- Campi di default obbligatori
      targa VARCHAR ( 32 ) NOT NULL,
      modello VARCHAR ( 128 ) NOT NULL,
      assi INT NOT NULL,
      classe_veicolo ENUM('A', 'B', '3', '4', '5') NOT NULL,

      -- Classe ambientale opzionale
      classe_ambientale VARCHAR ( 64 ),

      -- Anno immatricolazione opzionale
      anno_immatricolazione INT,

      -- Cilindrata opzionale
      cilindrata INT,

      -- Inquinamento acustico opzionale
      inquinamentoAcustico INT,

      UNIQUE(targa),
      PRIMARY KEY ( id )
);

CREATE TABLE IF NOT EXISTS autostrada (
      id BIGINT AUTO_INCREMENT,
      nome VARCHAR ( 255 ) NOT NULL,
      iva NOT NULL,
      PRIMARY KEY ( id )
);

CREATE TABLE IF NOT EXISTS casello (
      id BIGINT AUTO_INCREMENT,
      locazione VARCHAR ( 255 ) NOT NULL,
      kilometro INT NOT NULL,
      PRIMARY KEY ( id )
);

CREATE TABLE IF NOT EXISTS tariffa ( 
      id BIGINT AUTO_INCREMENT,
      classe_veicolo ENUM('A', 'B', '3', '4', '5') NOT NULL,
      prezzo DECIMAL ( 9, 2 ) NOT NULL,
      PRIMARY KEY ( id ),

      -- Foreign key id_autostrada riferimento a autostrada
      id_autostrada BIGINT,
      FOREIGN KEY ( id_autostrada ) REFERENCES autostrada ( id ),
);

CREATE TABLE IF NOT EXISTS biglietto ( 
      id BIGINT AUTO_INCREMENT,
      data_ora_emissione TIMESTAMP,
      data_ora_validazione TIMESTAMP,

      -- Foreign key id_casello riferimento a casello
      id_casello BIGINT,
      FOREIGN KEY ( id_casello ) REFERENCES casello ( id ),

      -- Foreign key id_veicolo riferimento a veicolo
      id_veicolo BIGINT,
      FOREIGN KEY ( id_veicolo ) REFERENCES veicolo ( id ),

      PRIMARY KEY ( id )
);


