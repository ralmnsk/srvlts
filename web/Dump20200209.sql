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
  `name_course` varchar(45) DEFAULT NULL,
  `id_tutor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_course`),
  UNIQUE KEY `idcourse_UNIQUE` (`id_course`),
  KEY `fk_id_tutor_idx` (`id_tutor`),
  CONSTRAINT `fk_id_tutor` FOREIGN KEY (`id_tutor`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'java',1),(2,'java3',2),(3,'java4',2),(4,'java5 - 1',1),(6,'java6.3 -1',1),(7,'kotlin z',3),(8,'kotlin z2',3),(9,'Кибернетика',3),(10,'Физика',1),(11,'Высшая математика',1),(12,'Электротехника',1),(13,'PHP',1),(14,'WEB Design',1),(15,'JUNIT\r\n',1),(16,'kurs 2-1',1),(17,'Химия',1),(18,'Рисование',1),(19,'Шахматы',1),(20,'Нарды',1),(21,'Математика',1),(22,'Паскаль',1),(23,'Английский',1),(24,'Футбол',1),(25,'Java Script',1),(26,'Kotlin',1),(27,'Сапромат',1),(28,'Робототехника',1),(29,'Ботаника',1),(30,'С+',1),(31,'Тестирование',1),(33,' курс 1',1),(34,'курс 2',1),(35,'курс 3',1),(36,'курс 4',1),(37,'курс 5',1),(39,'Angular',1),(40,'course 2',1),(42,'testing 2',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark`
--

LOCK TABLES `mark` WRITE;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` VALUES (4,4,5,8,'norm1'),(9,6,4,10,'ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста ревью много текста '),(13,14,4,6,'норм'),(14,18,4,NULL,NULL),(15,13,4,NULL,NULL),(16,17,4,NULL,NULL),(17,20,4,NULL,NULL),(18,9,4,NULL,NULL),(19,12,4,NULL,NULL),(20,15,4,NULL,NULL),(21,24,4,NULL,NULL),(22,25,4,NULL,NULL),(25,17,4,NULL,NULL),(26,19,4,NULL,NULL),(27,14,4,NULL,NULL),(28,1,4,NULL,NULL),(29,22,4,NULL,NULL),(32,28,4,NULL,NULL),(33,30,4,8,'extraordionary');
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
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_student_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'surname','name','qqq','qqq','TUTOR'),(2,'surname2','name2','qqq2','qqq2','TUTOR'),(3,'Fam','Ima','zzz','zzz','TUTOR'),(4,'Птичкин','Витольд','aaa','aaa','STUDENT'),(5,'bbb','bbb','bbb','bbb','STUDENT'),(6,'tests','testn','test','test','STUDENT');
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

-- Dump completed on 2020-02-09 18:56:07
