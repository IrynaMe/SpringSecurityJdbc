--- CREO UN DATABASE CHIAMATO dblogin
CREATE DATABASE dblogin;

USE dblogin;

DROP TABLE IF EXISTS utenti;
DROP TABLE IF EXISTS ruoli;

--
-- Struttura tabella utenti
--

CREATE TABLE utenti (
  username varchar(50) NOT NULL,
  password varchar(68) NOT NULL,
  enabled boolean NOT NULL,
  PRIMARY KEY (username)
);

--
--
--
-- la password è per tutti i ruoli: test123
--

INSERT INTO utenti
VALUES
('admin','{bcrypt}$2a$10$u10P0MCYyrLjevX0Hg.K2.9mUfGhoxzESxdVc/vygW0wl4XC942ai',1),
('guest','{bcrypt}$2a$10$K5VPzvjuAUFwjtjY2nkKNuKgPnZSsBF2jLeiQrcEDaAbasSNFgIKS',1),
('mario','{bcrypt}$2a$10$EbFdZ/EiD6R8dkz4S0k6ZOdK/u04fZO3SzIT3WIsYU/Wv.gGS1DIS',1),
('enzo','{bcrypt}$2a$10$iy3MMMVCg/aPGDtTRDp56uv8Cz5r4w82rIr.TN7VWzV1CV9QZoGwu',1),
('ugo','{bcrypt}$2a$10$Spm8hxx0ZPP87M67GdZ6MefDBTupzovHZwkF6ObA8/UTB17gyAiIW',1),
('anna','{bcrypt}$2a$10$5VNXOa/4QB.IcXq3mD8Sbek/bQ.Rmmg5BvhQuwz1KR7U5It7MKtCS',1);

--
-- STRUTTURA DELLA TABELLA `ruoli

CREATE TABLE ruoli (
  user varchar(50) NOT NULL,
  ruolo varchar(50) NOT NULL,
  UNIQUE KEY authorities4_idx_1 (user, ruolo),
  CONSTRAINT authorities4_ibfk_1 FOREIGN KEY (user) REFERENCES utenti (username)
);

--
-- Inserisco i dati nella tabella ruoli
-- I ruoli sono ADMIN, GUES, IMPIEGATO, MANAGER

INSERT INTO ruoli
VALUES 
('admin','ROLE_ADMIN'),
('guest','ROLE_GUEST'),
('mario','ROLE_IMPIEGATO'),
('ugo','ROLE_IMPIEGATO'),
('anna','ROLE_MANAGER'),
('enzo','ROLE_IMPIEGATO');
