-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2023 at 09:21 PM
-- Server version: 11.1.1-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `data`
--

-- --------------------------------------------------------

--
-- Table structure for table `carta`
--

CREATE TABLE `carta` (
  `id_palo` int(2) NOT NULL,
  `numero` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `carta`
--

INSERT INTO `carta` (`id_palo`, `numero`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 10),
(1, 11),
(1, 12),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 10),
(2, 11),
(2, 12),
(3, 1),
(3, 2),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 10),
(3, 11),
(3, 12),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(4, 7),
(4, 10),
(4, 11),
(4, 12);

-- --------------------------------------------------------

--
-- Table structure for table `palo`
--

CREATE TABLE `palo` (
  `id_palo` int(1) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `palo`
--

INSERT INTO `palo` (`id_palo`, `nombre`) VALUES
(1, 'Espada'),
(2, 'Basto'),
(3, 'Oro'),
(4, 'Copa');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `Usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `nombre_usuario` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `dinero` decimal(10,2) NOT NULL,
  `rol` varchar(40) NOT NULL,
  `activo` boolean not null
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `Usuario` (`id_usuario`, `nombre`, `apellido`, `email`, `nombre_usuario`, `password`, `dinero`,`rol`,`activo`) VALUES
(1, 'nombretest', 'apellidotest', 'test@unlam.edu.ar', 'nombreusuariotest', 'test', 10.00,'ADMIN',false);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carta`
--
ALTER TABLE `carta`
  ADD PRIMARY KEY (`id_palo`,`numero`);

--
-- Indexes for table `palo`
--
ALTER TABLE `palo`
  ADD PRIMARY KEY (`id_palo`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `Usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carta`
--
ALTER TABLE `carta`
  ADD CONSTRAINT `carta_ibfk_1` FOREIGN KEY (`id_palo`) REFERENCES `palo` (`id_palo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
