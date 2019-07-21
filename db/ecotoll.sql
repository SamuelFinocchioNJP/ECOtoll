-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 21, 2019 alle 21:05
-- Versione del server: 10.3.16-MariaDB
-- Versione PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecotoll`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `administrator`
--
CREATE DATABASE ecotoll;
USE ecotoll

CREATE TABLE `administrator` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `administrator`
--

INSERT INTO `administrator` (`id`, `username`, `password`) VALUES
(1, 'jimmy', 'admin');

-- --------------------------------------------------------

--
-- Struttura della tabella `autostrada`
--

CREATE TABLE `autostrada` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `iva` decimal(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `autostrada`
--

INSERT INTO `autostrada` (`id`, `nome`, `iva`) VALUES
(1, 'A18', '22.00'),
(2, 'A2', '22.00'),
(3, 'A3', '22.00'),
(4, 'A4', '22.00'),
(5, 'A5', '22.00'),
(6, 'A24', '22.00'),
(7, 'A25', '22.00'),
(8, 'A20', '22.00'),
(9, 'A21', '22.00'),
(10, 'A14', '22.00');

-- --------------------------------------------------------

--
-- Struttura della tabella `biglietto`
--

CREATE TABLE `biglietto` (
  `id` bigint(20) NOT NULL,
  `data_ora_emissione` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `data_ora_validazione` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `id_casello` bigint(20) DEFAULT NULL,
  `id_veicolo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `casello`
--

CREATE TABLE `casello` (
  `id` bigint(20) NOT NULL,
  `id_autostrada` bigint(20) NOT NULL,
  `locazione` varchar(255) NOT NULL,
  `kilometro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `casello`
--

INSERT INTO `casello` (`id`, `id_autostrada`, `locazione`, `kilometro`) VALUES
(1, 1, 'Teramo Ovest', 25),
(2, 1, 'L\'aquila est', 150),
(3, 1, 'Roma Est', 400);

-- --------------------------------------------------------

--
-- Struttura della tabella `tariffa`
--

CREATE TABLE `tariffa` (
  `id` bigint(20) NOT NULL,
  `classe_veicolo` enum('A','B','3','4','5') NOT NULL,
  `prezzo` decimal(9,2) NOT NULL,
  `id_autostrada` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `tariffa`
--

INSERT INTO `tariffa` (`id`, `classe_veicolo`, `prezzo`, `id_autostrada`) VALUES
(71, 'A', '0.45', 1),
(72, 'B', '0.45', 1),
(73, '3', '3.70', 1),
(74, '4', '2.80', 1),
(75, '5', '1.40', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `veicolo`
--

CREATE TABLE `veicolo` (
  `id` bigint(20) NOT NULL,
  `targa` varchar(32) NOT NULL,
  `modello` varchar(128) NOT NULL,
  `assi` int(11) NOT NULL,
  `classe_veicolo` enum('A','B','3','4','5') NOT NULL,
  `classe_ambientale` varchar(64) DEFAULT NULL,
  `anno_immatricolazione` int(11) DEFAULT NULL,
  `cilindrata` int(11) DEFAULT NULL,
  `inquinamentoAcustico` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `autostrada`
--
ALTER TABLE `autostrada`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `biglietto`
--
ALTER TABLE `biglietto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_casello` (`id_casello`),
  ADD KEY `id_veicolo` (`id_veicolo`);

--
-- Indici per le tabelle `casello`
--
ALTER TABLE `casello`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `tariffa`
--
ALTER TABLE `tariffa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_autostrada` (`id_autostrada`);

--
-- Indici per le tabelle `veicolo`
--
ALTER TABLE `veicolo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `targa` (`targa`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `administrator`
--
ALTER TABLE `administrator`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `autostrada`
--
ALTER TABLE `autostrada`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la tabella `biglietto`
--
ALTER TABLE `biglietto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `casello`
--
ALTER TABLE `casello`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT per la tabella `tariffa`
--
ALTER TABLE `tariffa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT per la tabella `veicolo`
--
ALTER TABLE `veicolo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `biglietto`
--
ALTER TABLE `biglietto`
  ADD CONSTRAINT `biglietto_ibfk_1` FOREIGN KEY (`id_casello`) REFERENCES `casello` (`id`),
  ADD CONSTRAINT `biglietto_ibfk_2` FOREIGN KEY (`id_veicolo`) REFERENCES `veicolo` (`id`);

--
-- Limiti per la tabella `tariffa`
--
ALTER TABLE `tariffa`
  ADD CONSTRAINT `tariffa_ibfk_1` FOREIGN KEY (`id_autostrada`) REFERENCES `autostrada` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
