-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: proje3
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `plots`
--

DROP TABLE IF EXISTS `plots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plots` (
  `plot_id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int DEFAULT NULL,
  `plot_type` varchar(45) DEFAULT 'EMPTY',
  `daily_food_expense` int DEFAULT NULL,
  `daily_item_expense` int DEFAULT NULL,
  `daily_money_expense` int DEFAULT NULL,
  `plot_grid` varchar(10) DEFAULT NULL,
  `satisdurumu` varchar(45) DEFAULT 'satilik',
  `cost` int DEFAULT '20000',
  PRIMARY KEY (`plot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plots`
--

LOCK TABLES `plots` WRITE;
/*!40000 ALTER TABLE `plots` DISABLE KEYS */;
INSERT INTO `plots` VALUES (1,1,'empty',NULL,NULL,NULL,'11','satilik',20000),(2,1,'empty',NULL,NULL,NULL,'12','satilik',20000),(3,1,'empty',NULL,NULL,NULL,'13','satilik',20000),(4,1,'empty',NULL,NULL,NULL,'21','satilik',20000),(5,1,'empty',NULL,NULL,NULL,'22','satilik',20000),(6,1,'empty',NULL,NULL,NULL,'23','satilik',20000),(7,1,'empty',NULL,NULL,NULL,'31','satilik',20000),(8,1,'empty',NULL,NULL,NULL,'32','satilik',20000),(9,1,'empty',NULL,NULL,NULL,'33','satilik',20000),(10,1,'empty',NULL,NULL,NULL,'41','satilik',20000),(11,1,'empty',NULL,NULL,NULL,'42','satilik',20000),(12,1,'empty',NULL,NULL,NULL,'43','satilik',20000);
/*!40000 ALTER TABLE `plots` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-18 23:26:18
