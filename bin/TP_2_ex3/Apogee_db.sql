-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping database structure for apogee
CREATE DATABASE IF NOT EXISTS `apogee` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `apogee`;

-- Creating user with password
CREATE USER 'admin@fp'@'localhost' IDENTIFIED BY 'admin123';

-- Granting privileges on the apogee database
GRANT ALL PRIVILEGES ON `apogee`.* TO 'admin@fp'@'localhost';

-- Dumping structure for table apogee.etudiant
CREATE TABLE IF NOT EXISTS `etudiant` (
  `cne` varchar(20) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `filire` varchar(50) NOT NULL,
  PRIMARY KEY (`cne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table apogee.etudiant: ~10 rows (approximately)
DELETE FROM `etudiant`;
INSERT INTO `etudiant` (`cne`, `nom`, `prenom`, `filire`) VALUES
	('CNE11223', 'EL HASSANI', 'Omar', 'IMA'),
	('CNE12345', 'Elba', 'Ahmed', 'eco'),
	('CNE44567', 'Salem', 'Salem', 'it'),
	('CNE67890', 'Sarah', 'Sarah', 'ce'),
	('CNE99876', 'ELZAAMI', 'Maria', 'kam'),
	('EEE111111', 'Omar', '12', 'IMA'),
	('K11111111', 'Omar', 'omar', 'aaaaa'),
	('K1222222', 'K1222222', 'K1222222', 'K1222222'),
	('omar', 'omar', 'omar', 'omar'),
	('Omar12', 'Omar', 'Omar', 'Omar');

-- Restoring previous settings
/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
