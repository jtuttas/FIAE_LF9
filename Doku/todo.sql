/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE TABLE IF NOT EXISTS `bearbeitet` (
  `eid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `ansprechpartner` char(1) DEFAULT NULL,
  PRIMARY KEY (`eid`,`tid`),
  KEY `FK__task` (`tid`),
  CONSTRAINT `FK__employee` FOREIGN KEY (`eid`) REFERENCES `employee` (`eid`),
  CONSTRAINT `FK__task` FOREIGN KEY (`tid`) REFERENCES `task` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELETE FROM `bearbeitet`;
/*!40000 ALTER TABLE `bearbeitet` DISABLE KEYS */;
INSERT INTO `bearbeitet` (`eid`, `tid`, `ansprechpartner`) VALUES
	(1, 2, 'y'),
	(1, 6, 'y'),
	(1, 8, 'y'),
	(1, 9, 'y'),
	(2, 2, NULL),
	(2, 7, 'y'),
	(3, 3, 'y'),
	(3, 4, 'y'),
	(4, 5, 'y'),
	(4, 7, NULL),
	(4, 8, NULL),
	(4, 9, NULL);
/*!40000 ALTER TABLE `bearbeitet` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `customer` (
  `projid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL DEFAULT '0',
  `Str` varchar(80) NOT NULL DEFAULT '0',
  `Hausnummer` varchar(6) NOT NULL DEFAULT '0',
  `PLZ` char(6) NOT NULL DEFAULT '0',
  `Ort` varchar(80) NOT NULL DEFAULT '0',
  `trustlevel` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`projid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`projid`, `name`, `Str`, `Hausnummer`, `PLZ`, `Ort`, `trustlevel`) VALUES
	(1, 'JOBE GmbH', 'Hauptstraße', '2', '30539', 'Hannover', 1),
	(2, 'Schulte OHG', 'Nebenstraße', '15b', '31134', 'Hildesheim', 0.8),
	(3, 'Neo AG', 'Lissabonner Alle', '12', '30539', 'Hannover', 1),
	(4, 'Matrix Solution GmbH', 'Eilenriede', '5b', '30236', 'Hannover', 0.9),
	(5, 'DF Fenster KG', 'Limmerstraße', '22', '30532', 'Hannover', 0.7),
	(6, 'KFZ Walter', 'Hauptstraße', '23', '20236', 'Nebenborn', 0.6),
	(7, 'Paul Herbig GmbH', 'Seitenstraße', '23', '31143', 'Hildesheim', 0.7);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `employee` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` varchar(80) NOT NULL DEFAULT '0',
  `nachname` varchar(80) NOT NULL DEFAULT '0',
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`eid`, `vorname`, `nachname`) VALUES
	(1, 'Max', 'Mustermann'),
	(2, 'Simone', 'Schlau'),
	(3, 'Simone', 'Musterfrau'),
	(4, 'Tim', 'Taler'),
	(5, 'Susi', 'Sorglos'),
	(6, 'Frank', 'Fabian');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `priority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` int(11) NOT NULL DEFAULT '0',
  `description` varchar(80) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

DELETE FROM `priority`;
/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
INSERT INTO `priority` (`id`, `value`, `description`) VALUES
	(1, 0, 'hoch'),
	(2, 2, 'mittel'),
	(3, 4, 'niedrig'),
	(4, 6, 'gering');
/*!40000 ALTER TABLE `priority` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `task` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL DEFAULT '0',
  `priid` int(11) NOT NULL DEFAULT '0',
  `proid` int(11) NOT NULL DEFAULT '0',
  `date` date NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `FK__priority` (`priid`),
  KEY `FK__customer` (`proid`),
  CONSTRAINT `FK__customer` FOREIGN KEY (`proid`) REFERENCES `customer` (`projid`),
  CONSTRAINT `FK__priority` FOREIGN KEY (`priid`) REFERENCES `priority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

DELETE FROM `task`;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` (`tid`, `title`, `priid`, `proid`, `date`) VALUES
	(2, 'DBMS auswählen', 1, 1, '2020-04-12'),
	(3, 'Webseiten gestalten', 3, 1, '2020-05-13'),
	(4, 'Webserver installieren', 3, 1, '2020-06-27'),
	(5, 'DB anlegen', 1, 2, '2020-06-19'),
	(6, 'Login Funktionalität', 2, 2, '2020-07-22'),
	(7, 'Datenbankabfragen', 2, 2, '2020-07-30'),
	(8, 'Dokumentation', 4, 1, '2020-10-29'),
	(9, 'Dokumentation', 4, 2, '2020-10-29');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
