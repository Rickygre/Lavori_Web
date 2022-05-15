-- MySQL dump 10.19  Distrib 10.3.34-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: centro_medico
-- ------------------------------------------------------
-- Server version	10.3.34-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary table structure for view `costi_annuali`
--

DROP TABLE IF EXISTS `costi_annuali`;
/*!50001 DROP VIEW IF EXISTS `costi_annuali`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `costi_annuali` (
  `anno` tinyint NOT NULL,
  `mese` tinyint NOT NULL,
  `totale_costi` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `fullview`
--

DROP TABLE IF EXISTS `fullview`;
/*!50001 DROP VIEW IF EXISTS `fullview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `fullview` (
  `idt_prenot` tinyint NOT NULL,
  `t_prezzo_esame` tinyint NOT NULL,
  `t_data` tinyint NOT NULL,
  `t_quantità` tinyint NOT NULL,
  `t_nome` tinyint NOT NULL,
  `t_cognome` tinyint NOT NULL,
  `t_nome_visita` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `t_esami`
--

DROP TABLE IF EXISTS `t_esami`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_esami` (
  `idt_esame` int(11) NOT NULL AUTO_INCREMENT,
  `t_nome_visita` varchar(45) NOT NULL,
  `t_descrizione` varchar(45) NOT NULL,
  PRIMARY KEY (`idt_esame`),
  KEY `index2` (`t_nome_visita`,`t_descrizione`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_esami`
--

LOCK TABLES `t_esami` WRITE;
/*!40000 ALTER TABLE `t_esami` DISABLE KEYS */;
INSERT INTO `t_esami` VALUES (4,'ecg','elettrocardiogramma'),(6,'emocromo','esami_del_sangue'),(7,'neurologica','neuro'),(8,'odontoiatra','protesi_denti'),(9,'otorinolaringoiatra','udito'),(5,'rxmano','radiografia_arti'),(10,'specialistica_arti','rx');
/*!40000 ALTER TABLE `t_esami` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pazienti`
--

DROP TABLE IF EXISTS `t_pazienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_pazienti` (
  `idt_Utente` int(11) NOT NULL AUTO_INCREMENT,
  `t_nome` varchar(45) NOT NULL,
  `t_cognome` varchar(45) NOT NULL,
  `t_email` varchar(45) NOT NULL,
  PRIMARY KEY (`idt_Utente`),
  KEY `index2` (`t_email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pazienti`
--

LOCK TABLES `t_pazienti` WRITE;
/*!40000 ALTER TABLE `t_pazienti` DISABLE KEYS */;
INSERT INTO `t_pazienti` VALUES (1,'luca','rossi','lucar@libero.it'),(2,'mario','verdi','marvv@gmail.com'),(3,'sara','pozzo','pozzss@libero.it'),(4,'riccardo','santi','ricky@libero.it');
/*!40000 ALTER TABLE `t_pazienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prenotazioni`
--

DROP TABLE IF EXISTS `t_prenotazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_prenotazioni` (
  `idt_prenot` int(11) NOT NULL AUTO_INCREMENT,
  `t_prezzo_esame` decimal(6,2) NOT NULL,
  `t_data` datetime NOT NULL DEFAULT current_timestamp(),
  `idt_Utente` int(11) NOT NULL,
  `idt_esame` int(11) NOT NULL,
  `t_quantità` int(11) NOT NULL,
  PRIMARY KEY (`idt_prenot`),
  KEY `fkp_idx` (`idt_Utente`),
  KEY `fke_idx` (`idt_esame`),
  CONSTRAINT `fke` FOREIGN KEY (`idt_esame`) REFERENCES `t_esami` (`idt_esame`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkp` FOREIGN KEY (`idt_Utente`) REFERENCES `t_pazienti` (`idt_Utente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prenotazioni`
--

LOCK TABLES `t_prenotazioni` WRITE;
/*!40000 ALTER TABLE `t_prenotazioni` DISABLE KEYS */;
INSERT INTO `t_prenotazioni` VALUES (7,25.00,'2022-02-10 11:23:38',1,5,1),(9,35.00,'2022-02-20 11:23:38',3,7,2),(10,15.00,'2022-03-18 11:23:38',2,6,1),(11,65.00,'2022-05-10 11:23:38',4,9,3),(12,70.00,'2021-10-09 08:00:00',2,10,2),(13,85.00,'2021-11-15 10:00:15',4,4,1),(14,35.00,'2021-12-21 16:30:00',1,7,1),(15,15.00,'2021-11-04 12:30:00',2,6,2),(16,28.00,'2022-01-13 11:00:00',3,8,3),(17,70.00,'2022-02-23 18:00:00',2,10,1),(18,15.00,'2021-10-01 09:30:00',1,6,1),(19,85.00,'2022-04-02 10:00:00',2,4,2),(20,25.00,'2022-04-12 15:00:00',1,5,1),(21,85.00,'2021-11-16 13:30:00',3,4,1),(22,85.00,'2021-11-26 17:30:00',2,4,1);
/*!40000 ALTER TABLE `t_prenotazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `costi_annuali`
--

/*!50001 DROP TABLE IF EXISTS `costi_annuali`*/;
/*!50001 DROP VIEW IF EXISTS `costi_annuali`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`tss`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `costi_annuali` AS select year(`fullview`.`t_data`) AS `anno`,month(`fullview`.`t_data`) AS `mese`,sum(`fullview`.`t_prezzo_esame` * `fullview`.`t_quantità`) AS `totale_costi` from `fullview` group by year(`fullview`.`t_data`),month(`fullview`.`t_data`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `fullview`
--

/*!50001 DROP TABLE IF EXISTS `fullview`*/;
/*!50001 DROP VIEW IF EXISTS `fullview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`tss`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `fullview` AS select `pre`.`idt_prenot` AS `idt_prenot`,`pre`.`t_prezzo_esame` AS `t_prezzo_esame`,`pre`.`t_data` AS `t_data`,`pre`.`t_quantità` AS `t_quantità`,`paz`.`t_nome` AS `t_nome`,`paz`.`t_cognome` AS `t_cognome`,`esa`.`t_nome_visita` AS `t_nome_visita` from ((`t_prenotazioni` `pre` join `t_pazienti` `paz` on(`pre`.`idt_Utente` = `paz`.`idt_Utente`)) join `t_esami` `esa` on(`pre`.`idt_esame` = `esa`.`idt_esame`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-10 16:43:31
