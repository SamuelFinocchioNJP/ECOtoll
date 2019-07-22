-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 22, 2019 alle 21:48
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
(3, 1, 'Roma Est', 400),
(74, 18, 'Napoli nord', 0),
(75, 18, 'Bologna sud', 500),
(76, 18, 'Milano sud', 700),
(77, 18, 'Milano nord', 740),
(78, 19, 'Cosenza sud', 25),
(79, 19, 'Catanzaro', 50);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `casello`
--
ALTER TABLE `casello`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_autostrada` (`id_autostrada`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `casello`
--
ALTER TABLE `casello`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `casello`
--
ALTER TABLE `casello`
  ADD CONSTRAINT `casello_ibfk_1` FOREIGN KEY (`id_autostrada`) REFERENCES `autostrada` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
