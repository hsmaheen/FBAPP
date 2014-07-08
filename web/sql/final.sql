CREATE DATABASE  IF NOT EXISTS `shopping` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shopping`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: shopping
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `CartID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `CPrice` float NOT NULL,
  PRIMARY KEY (`CartID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CName` varchar(45) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Fruits'),(2,'Vegetables'),(3,'Aerated Drinks');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `userid` varchar(255) NOT NULL,
  `groupname` varchar(255) NOT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `fk` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES ('1212331','member'),('ashwin','member'),('bala','member'),('Bala Pilla','member'),('darren','member'),('hilla','member'),('hsmaheen','member');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetails` (
  `OrderDetailsID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) NOT NULL,
  `OrderID` int(11) NOT NULL,
  `OrderQuant` int(11) NOT NULL,
  `OPrice` double NOT NULL,
  PRIMARY KEY (`OrderDetailsID`),
  KEY `ProductID_idx` (`ProductID`),
  KEY `OrderID_idx` (`OrderID`),
  CONSTRAINT `OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProductID` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,2,4,100,100),(2,1,4,100,100),(3,1,5,100,100),(4,2,5,100,100),(5,2,6,1,100),(6,1,6,1,100),(7,1,7,6,100),(8,2,7,13,100),(9,1,36,1,100),(10,2,36,1,100),(11,2,37,1,100),(12,1,37,1,100),(13,1,38,1,100),(14,1,49,4,40),(15,2,49,2,20),(16,1,50,2,20),(17,2,50,2,20),(18,1,51,1,10),(19,2,51,1,10),(20,2,52,1,10),(21,1,52,1,10),(22,2,53,1,10),(23,1,53,1,10),(24,2,54,4,40),(25,1,54,1,10),(26,1,55,2,20),(27,2,55,3,30),(28,2,56,2,20),(29,1,56,1,10),(30,2,57,2,20),(31,1,57,2,20),(32,1,58,1,10),(33,2,58,1,10),(34,1,59,2,20),(35,2,59,2,20),(36,2,60,5,50);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `OrderAddress` varchar(45) DEFAULT NULL,
  `OTotPrice` float NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `fk_idx` (`userid`),
  CONSTRAINT `fk_p` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'hsmaheen','2014-02-11 00:21:19','SINGAPORE',100),(4,'hsmaheen','2014-02-11 00:54:28','SINGAPORE',100),(5,'Bala Pilla','2014-02-11 00:57:42','SINGAPORE',100),(6,'ashwin','2014-02-11 01:37:21','Singapore',100),(7,'ashwin','2014-02-11 01:58:33','Singapore',100),(8,'ashwin','2014-02-11 02:04:24','Singapore',100),(9,'ashwin','2014-02-11 02:06:48','Singapore',100),(10,'ashwin','2014-02-11 02:06:48','Singapore',100),(11,'ashwin','2014-02-11 02:06:49','Singapore',100),(12,'ashwin','2014-02-11 02:06:49','Singapore',100),(13,'ashwin','2014-02-11 02:06:49','Singapore',100),(14,'ashwin','2014-02-11 02:06:49','Singapore',100),(15,'ashwin','2014-02-11 02:06:49','Singapore',100),(16,'ashwin','2014-02-11 02:06:49','Singapore',100),(17,'ashwin','2014-02-11 02:06:50','Singapore',100),(18,'ashwin','2014-02-11 02:06:50','Singapore',100),(19,'ashwin','2014-02-11 02:06:50','Singapore',100),(20,'ashwin','2014-02-11 02:06:50','Singapore',100),(21,'ashwin','2014-02-11 02:06:50','Singapore',100),(22,'ashwin','2014-02-11 02:06:51','Singapore',100),(23,'ashwin','2014-02-11 02:06:51','Singapore',100),(24,'ashwin','2014-02-11 02:06:51','Singapore',100),(25,'ashwin','2014-02-11 02:06:51','Singapore',100),(26,'ashwin','2014-02-11 02:06:52','Singapore',100),(27,'ashwin','2014-02-11 02:06:52','Singapore',100),(28,'ashwin','2014-02-11 02:06:53','Singapore',100),(29,'ashwin','2014-02-11 02:06:53','Singapore',100),(30,'ashwin','2014-02-11 02:06:54','Singapore',100),(31,'ashwin','2014-02-11 02:06:54','Singapore',100),(32,'ashwin','2014-02-11 02:06:54','Singapore',100),(33,'ashwin','2014-02-11 02:06:54','Singapore',100),(34,'ashwin','2014-02-11 02:06:55','Singapore',100),(35,'ashwin','2014-02-11 02:06:55','Singapore',100),(36,'ashwin','2014-02-11 02:06:59','Singapore',100),(37,'ashwin','2014-02-11 02:08:23','Singapore',100),(38,'ashwin','2014-02-11 02:12:32','Singapore',100),(39,'ashwin','2014-02-11 02:12:34','Singapore',100),(40,'ashwin','2014-02-11 02:12:34','Singapore',100),(41,'ashwin','2014-02-11 02:12:35','Singapore',100),(42,'ashwin','2014-02-11 02:12:35','Singapore',100),(43,'ashwin','2014-02-11 02:12:35','Singapore',100),(44,'ashwin','2014-02-11 02:13:58','Singapore',100),(45,'ashwin','2014-02-11 02:13:58','Singapore',100),(46,'ashwin','2014-02-11 02:13:58','Singapore',100),(47,'ashwin','2014-02-11 02:13:58','Singapore',100),(48,'ashwin','2014-02-11 02:13:59','Singapore',100),(49,'ashwin','2014-02-11 02:42:03','Singapore',100),(50,'ashwin','2014-02-11 02:45:26','Singapore',100),(51,'ashwin','2014-02-11 03:07:49','Singapore',100),(52,'ashwin','2014-02-11 03:08:02','Singapore',100),(53,'hsmaheen','2014-02-11 07:17:00','SINGAPORE',100),(54,'hsmaheen','2014-02-11 07:17:15','SINGAPORE',100),(55,'hsmaheen','2014-02-11 07:25:44','SINGAPORE',100),(56,'hsmaheen','2014-02-11 07:27:00','SINGAPORE',100),(57,'hsmaheen','2014-02-11 07:32:36','SINGAPORE',100),(58,'hsmaheen','2014-02-11 07:39:33','SINGAPORE',20),(59,'hsmaheen','2014-02-11 07:43:28','SINGAPORE',40),(60,'hsmaheen','2014-02-11 08:06:49','SINGAPORE',50);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `PCatID` int(11) NOT NULL,
  `PName` varchar(45) NOT NULL,
  `PDescription` varchar(45) NOT NULL,
  `PricePerUnit` float NOT NULL,
  `PQuanity` int(11) NOT NULL,
  `PImg` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `CategoryID_idx` (`PCatID`),
  CONSTRAINT `CategoryID` FOREIGN KEY (`PCatID`) REFERENCES `category` (`CategoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,'Apples','Apples are good away',10,987,' images/apples.jpg'),(2,1,'Grapes','Grapes are juicy',10,77,'images/grapes.jpg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `UserType` varchar(50) NOT NULL,
  `Membership` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1212331','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','member','Normal','Maheen','hsmaheen@gmail.com','212323','86463605'),('ashwin','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','member','Normal','Ashwin Kumar','mvashwinkumar@gmail.com','Singapore','08098080'),('bala','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','member','Normal','Bala ','bala.8709@gmail.com','Singapore','0980809'),('Bala Pilla','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','member','Normal','bala','bala.8709@gmail.com','SINGAPORE','90890808'),('darren','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','member','Normal','Darren','jdzjay@gmail.com','80988','8080'),('hilla','f7abf2a084c3668c7b90654bf01205085e5d0219ffad0564904e5c923af11523','member','Normal','Maheen','abc@gmail.com','dssadgt','86463605'),('hsmaheen','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','member','Normal','Maheen','hsmaheen@gmail.com','SINGAPORE','86463605');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-11  8:09:59
