-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2021 at 03:04 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `karateklub`
--

-- --------------------------------------------------------

--
-- Table structure for table `clan`
--

CREATE TABLE `clan` (
  `clanID` int(11) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `JMBG` bigint(20) NOT NULL,
  `pojas` varchar(255) NOT NULL,
  `timID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clan`
--

INSERT INTO `clan` (`clanID`, `ime`, `prezime`, `JMBG`, `pojas`, `timID`) VALUES
(50, 'Nikolina', 'Papic', 23049382010, 'CRNI', NULL),
(51, 'Milan', 'Brkic', 1192019712, 'BRAON', NULL),
(53, 'Mihajlo', 'Rakic', 331214521, 'NARANDZASTI', NULL),
(55, 'Isidora', 'Stankovic', 240822032123, 'NARANDZASTI', 44),
(56, 'Katarina', 'Veljovic', 9928319123, 'CRVENI', 44),
(57, 'Milica', 'Bogdanovic', 22103912031, 'PLAVI', NULL),
(58, 'Sava', 'Vukmirovic', 2910923, 'PLAVI', NULL),
(59, 'Luka', 'Radosevic', 32083210, 'NARANDZASTI', 48),
(61, 'Srdjan', 'Drecun', 483494713, 'NARANDZASTI', NULL),
(62, 'Olga', 'Tasic', 9231793293, 'PLAVI', 46),
(68, 'Petar', 'Peric', 829232810, 'CRVENI', 45),
(70, 'Marko', 'Petrovic', 2938213, 'BELI', 38),
(71, 'Jovan', 'Stefanov', 2923802, 'NARANDZASTI', NULL),
(72, 'Andjela', 'Jevtic', 39102382931, 'ZELENI', NULL),
(74, 'Milos', 'Tasic', 91203821398, 'ZELENI', NULL),
(75, 'Renata', 'Jevtic', 129304712039, 'BELI', 48),
(76, 'Vladan', 'Makovic', 90329381293, 'NARANDZASTI', 46),
(77, 'Sanja', 'Zlatanovic', 32903293812, 'ZELENI', NULL),
(78, 'Ana', 'Anic', 2938249, 'BELI', 45),
(79, 'Novak', 'Novakovic', 29308294812, 'ZUTI', 38),
(80, 'Stefan', 'Novakovic', 12349372163, 'BELI', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `polaganje`
--

CREATE TABLE `polaganje` (
  `polaganjeID` int(11) NOT NULL,
  `datum` date NOT NULL,
  `mesto` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `polaganje`
--

INSERT INTO `polaganje` (`polaganjeID`, `datum`, `mesto`) VALUES
(1, '2021-04-12', 'Beograd, Zvezdara'),
(2, '2021-05-02', 'Beograd, Palilula'),
(3, '2021-03-02', 'Beograd, Medakovic'),
(4, '2021-02-28', 'Beograd.Cukarica'),
(5, '2021-04-29', 'Beograd, Stari Grad'),
(6, '2021-03-02', 'Novi Sad'),
(7, '2021-05-10', 'Cacak'),
(8, '2021-08-08', 'Beograd, Zvezdara'),
(9, '2021-06-06', 'Sucin'),
(10, '2021-08-01', 'Novi Pazar'),
(11, '2021-08-01', 'Ugrinovci'),
(12, '2021-03-12', 'Kragujevac'),
(13, '2021-05-18', 'Vrbas'),
(14, '2021-04-12', 'Beograd'),
(15, '2021-05-01', 'Surcin'),
(16, '2021-05-01', 'Paracin');

-- --------------------------------------------------------

--
-- Table structure for table `rezultatpolaganja`
--

CREATE TABLE `rezultatpolaganja` (
  `clanID` int(11) NOT NULL,
  `polaganjeID` int(11) NOT NULL,
  `polozio` tinyint(1) DEFAULT NULL,
  `pojas` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rezultatpolaganja`
--

INSERT INTO `rezultatpolaganja` (`clanID`, `polaganjeID`, `polozio`, `pojas`) VALUES
(51, 1, 1, 'CRNI'),
(53, 1, 1, 'ZUTI'),
(53, 2, 1, 'NARANDZASTI'),
(53, 5, 1, 'CRVENI'),
(53, 8, 1, 'CRVENI'),
(57, 1, 1, 'PLAVI'),
(57, 3, 0, 'PLAVI'),
(57, 4, 0, 'PLAVI'),
(57, 7, 1, 'ZELENI'),
(58, 1, 1, 'PLAVI'),
(58, 5, 1, 'BRAON'),
(59, 1, 1, 'CRVENI');

-- --------------------------------------------------------

--
-- Table structure for table `statistikatakmicara`
--

CREATE TABLE `statistikatakmicara` (
  `takmicarID` int(11) NOT NULL,
  `takmicenjeID` int(11) NOT NULL,
  `plasman` varchar(255) NOT NULL,
  `brojPoena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `statistikatakmicara`
--

INSERT INTO `statistikatakmicara` (`takmicarID`, `takmicenjeID`, `plasman`, `brojPoena`) VALUES
(26, 2, 'Finale - 1. mesto', 25),
(26, 4, 'Osmina finala', 0),
(26, 7, '5. mesto', 5),
(32, 1, '5. mesto', 5),
(32, 2, '1', 1),
(32, 3, '1', 1),
(35, 5, '6. mesto', 0),
(37, 1, 'Polufinale - 3.mesto', 18),
(39, 1, '10. mesto', 0),
(42, 1, '1. mesto', 25),
(42, 4, '10. mesto', 0),
(42, 5, '1. mesto', 25),
(43, 1, '19. mesto', 0);

-- --------------------------------------------------------

--
-- Table structure for table `takmicar`
--

CREATE TABLE `takmicar` (
  `takmicarID` int(11) NOT NULL,
  `tim` tinyint(1) DEFAULT NULL,
  `pojedinacno` tinyint(1) DEFAULT NULL,
  `kategorija` varchar(255) NOT NULL,
  `timID` int(11) DEFAULT NULL,
  `clanID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `takmicar`
--

INSERT INTO `takmicar` (`takmicarID`, `tim`, `pojedinacno`, `kategorija`, `timID`, `clanID`) VALUES
(26, 1, 1, 'JUNIOR', 46, 62),
(32, 1, 1, 'JUNIOR', 44, 55),
(34, 0, 1, 'KADET', NULL, 50),
(35, 0, 1, 'PIONIR', NULL, 58),
(37, 1, 1, 'POLETARAC', 48, 59),
(38, 0, 1, 'SENIOR', NULL, 71),
(39, 1, 1, 'POLETARAC', 45, 78),
(42, 0, 1, 'PIONIR', NULL, 51),
(43, 1, 1, 'POLETARAC', 38, 70),
(44, 1, 1, 'JUNIOR', 38, 79);

-- --------------------------------------------------------

--
-- Table structure for table `takmicenje`
--

CREATE TABLE `takmicenje` (
  `takmicenjeID` int(11) NOT NULL,
  `datum` date NOT NULL,
  `mesto` varchar(255) NOT NULL,
  `naziv` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `takmicenje`
--

INSERT INTO `takmicenje` (`takmicenjeID`, `datum`, `mesto`, `naziv`) VALUES
(1, '2021-03-02', 'Beograd', 'Kup Beograda'),
(2, '2021-04-08', 'Cacak', 'Dani Cacka'),
(3, '2021-03-18', 'Zlatibor', 'Zlatiborski dani'),
(4, '2021-05-05', 'Beograd', 'Pobednik Beograda'),
(5, '2021-09-08', 'Kragujevac', 'Kragujevacki dani'),
(6, '2021-03-12', 'Beograd', 'Milorad Petrovic'),
(7, '2021-02-20', 'Sabac', 'Pobednik'),
(8, '2021-05-05', 'Beograd', 'Pehar Beograda'),
(9, '2021-05-05', 'Subotica', 'Suboticki dani');

-- --------------------------------------------------------

--
-- Table structure for table `tim`
--

CREATE TABLE `tim` (
  `timID` int(11) NOT NULL,
  `timBorbe` tinyint(1) DEFAULT NULL,
  `timKate` tinyint(1) DEFAULT NULL,
  `timTakmicar` tinyint(1) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tim`
--

INSERT INTO `tim` (`timID`, `timBorbe`, `timKate`, `timTakmicar`, `naziv`) VALUES
(38, 1, 0, 1, 'Tim borbe takmicarski'),
(40, 1, 1, 1, 'Tim borbe i kate - takmicarski'),
(44, 1, 1, 1, 'Tim borbe i kate 2'),
(45, 1, 0, 0, 'Tim borbe'),
(46, 1, 0, 0, 'Tim borbe 2'),
(47, 1, 0, 1, 'Tim 2'),
(48, 1, 1, 0, 'Tim 1'),
(50, 1, 0, 0, 'Tim borbe 3'),
(51, 0, 1, 0, 'Tim kate');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clan`
--
ALTER TABLE `clan`
  ADD PRIMARY KEY (`clanID`),
  ADD KEY `timID` (`timID`);

--
-- Indexes for table `polaganje`
--
ALTER TABLE `polaganje`
  ADD PRIMARY KEY (`polaganjeID`);

--
-- Indexes for table `rezultatpolaganja`
--
ALTER TABLE `rezultatpolaganja`
  ADD PRIMARY KEY (`clanID`,`polaganjeID`),
  ADD KEY `polaganjeID` (`polaganjeID`);

--
-- Indexes for table `statistikatakmicara`
--
ALTER TABLE `statistikatakmicara`
  ADD PRIMARY KEY (`takmicarID`,`takmicenjeID`),
  ADD KEY `takmicenjeID` (`takmicenjeID`);

--
-- Indexes for table `takmicar`
--
ALTER TABLE `takmicar`
  ADD PRIMARY KEY (`takmicarID`),
  ADD KEY `clanID` (`clanID`),
  ADD KEY `timID` (`timID`);

--
-- Indexes for table `takmicenje`
--
ALTER TABLE `takmicenje`
  ADD PRIMARY KEY (`takmicenjeID`);

--
-- Indexes for table `tim`
--
ALTER TABLE `tim`
  ADD PRIMARY KEY (`timID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clan`
--
ALTER TABLE `clan`
  MODIFY `clanID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT for table `polaganje`
--
ALTER TABLE `polaganje`
  MODIFY `polaganjeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `takmicar`
--
ALTER TABLE `takmicar`
  MODIFY `takmicarID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `takmicenje`
--
ALTER TABLE `takmicenje`
  MODIFY `takmicenjeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tim`
--
ALTER TABLE `tim`
  MODIFY `timID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clan`
--
ALTER TABLE `clan`
  ADD CONSTRAINT `clan_ibfk_1` FOREIGN KEY (`timID`) REFERENCES `tim` (`timID`);

--
-- Constraints for table `rezultatpolaganja`
--
ALTER TABLE `rezultatpolaganja`
  ADD CONSTRAINT `rezultatpolaganja_ibfk_1` FOREIGN KEY (`clanID`) REFERENCES `clan` (`clanID`),
  ADD CONSTRAINT `rezultatpolaganja_ibfk_2` FOREIGN KEY (`polaganjeID`) REFERENCES `polaganje` (`polaganjeID`);

--
-- Constraints for table `statistikatakmicara`
--
ALTER TABLE `statistikatakmicara`
  ADD CONSTRAINT `statistikatakmicara_ibfk_1` FOREIGN KEY (`takmicarID`) REFERENCES `takmicar` (`takmicarID`),
  ADD CONSTRAINT `statistikatakmicara_ibfk_2` FOREIGN KEY (`takmicenjeID`) REFERENCES `takmicenje` (`takmicenjeID`);

--
-- Constraints for table `takmicar`
--
ALTER TABLE `takmicar`
  ADD CONSTRAINT `takmicar_ibfk_2` FOREIGN KEY (`clanID`) REFERENCES `clan` (`clanID`),
  ADD CONSTRAINT `takmicar_ibfk_3` FOREIGN KEY (`timID`) REFERENCES `tim` (`timID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
