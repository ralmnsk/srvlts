CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id_course` int(11) NOT NULL AUTO_INCREMENT,
  `name_course` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `id_tutor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_course`),
  UNIQUE KEY `idcourse_UNIQUE` (`id_course`),
  KEY `fk_id_tutor_idx` (`id_tutor`),
  CONSTRAINT `fk_id_tutor` FOREIGN KEY (`id_tutor`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (139,'курс1','курс1',44),(140,'курс2','курс2',44),(141,'курс3','курс3',44),(142,'курс4','курс4',44),(143,'курс5','курс5',44),(144,'курс6','курс6',44),(145,'курс7','курс7',44),(146,'курс8','курс8',44),(147,'курс9','курс9 редактирование',44),(148,'курс10','курс10 редактирование',44),(150,'курс21','курс21',47),(151,'курс22','курс22',47),(152,'курс23','курс23',47),(153,'курс24','курс24',47),(154,'курс25','курс25',47),(155,'курс 29','курс 29',44),(156,'курс25','курс25',44),(157,'курс 28','курс  28',44),(158,'курс 30 редактирование','курс  30 редактирование1',47),(159,'курс 41','курс 41',44),(160,'курс 42 edit','курс 42 edit',44),(161,'курс 5','курс 5',44),(162,'cource 1','cource 1',44),(163,'course 2','course 2',44),(164,'course 3 edit','course 3 edit 2',44);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mark` (
  `id_mark` int(11) NOT NULL AUTO_INCREMENT,
  `id_course` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `mark` int(11) DEFAULT NULL,
  `review` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id_mark`),
  UNIQUE KEY `id_mark_UNIQUE` (`id_mark`),
  KEY `fk_id_course_idx` (`id_course`),
  KEY `fk_id_student_idx` (`id_student`),
  CONSTRAINT `fk_id_course_mark` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`),
  CONSTRAINT `fk_id_student` FOREIGN KEY (`id_student`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark`
--

LOCK TABLES `mark` WRITE;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` VALUES (99,139,45,9,'курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс курс'),(100,147,45,9,'review'),(101,140,45,NULL,NULL),(102,143,45,NULL,NULL),(103,158,48,NULL,NULL),(104,157,48,NULL,NULL),(105,156,48,NULL,NULL),(106,155,48,NULL,NULL),(107,153,48,NULL,NULL),(108,152,48,NULL,NULL),(109,151,48,NULL,NULL),(111,164,45,NULL,NULL);
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_student_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (44,'Иван','Иванов','tutor','$2a$06$bLUG805Was5kvgVy945w7egwqZhms8oZQrl7Bivxg9ZnRYsafkida','TUTOR'),(45,'Степан','Степанов','student','$2a$06$yw7KM.8kLVaHpAbQ4xuuVe9w4Qdl7a4ZGYeBJ780VHfnVVUrVxury','STUDENT'),(47,'ФамилияА','ИмяА','tutor2','$2a$06$zE.I9H4x9zjaj7CUd6DTMuYflLuD.uhnF.WZKd5G4FMaFDALPp/hi','TUTOR'),(48,'surnameB','nameB','student2','$2a$06$/dJ77NUOgS76.86DFcF4k.ltENHi92/rqMSQTba0Xg2J.SEd7We3C','STUDENT');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-10 22:39:02
