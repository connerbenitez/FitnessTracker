CREATE DATABASE  IF NOT EXISTS `fitness_tracker` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fitness_tracker`;
-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: localhost    Database: fitness_tracker
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `Diet`
--

DROP TABLE IF EXISTS `Diet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Diet` (
  `diet_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `diet_goal_id` int DEFAULT NULL,
  `food_id` int DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`diet_id`),
  KEY `food_id_idx` (`food_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `diet_goal_id_fk4_idx` (`diet_goal_id`),
  CONSTRAINT `` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`),
  CONSTRAINT `diet_goal_id_fk4` FOREIGN KEY (`diet_goal_id`) REFERENCES `DietGoal` (`diet_goal_id`),
  CONSTRAINT `food_id` FOREIGN KEY (`food_id`) REFERENCES `Food` (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Diet`
--

LOCK TABLES `Diet` WRITE;
/*!40000 ALTER TABLE `Diet` DISABLE KEYS */;
INSERT INTO `Diet` VALUES (1,1,NULL,1,'2025-12-02 08:00:00','2025-12-02'),(2,1,NULL,4,'2025-12-02 10:30:00','2025-12-02'),(3,2,NULL,2,'2025-12-02 12:45:00','2025-12-02'),(4,2,NULL,3,'2025-12-02 12:45:00','2025-12-02'),(5,3,NULL,5,'2025-12-02 15:00:00','2025-12-02'),(6,4,NULL,6,'2025-12-02 07:30:00','2025-12-02'),(7,4,NULL,7,'2025-12-02 13:00:00','2025-12-02'),(8,4,NULL,8,'2025-12-02 13:00:00','2025-12-02'),(9,5,NULL,9,'2025-12-03 06:45:00','2025-12-03'),(10,5,NULL,2,'2025-12-03 12:00:00','2025-12-03');
/*!40000 ALTER TABLE `Diet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DietGoal`
--

DROP TABLE IF EXISTS `DietGoal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DietGoal` (
  `diet_goal_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `completion` tinyint NOT NULL DEFAULT '0',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `calorie_goal` int DEFAULT NULL,
  PRIMARY KEY (`diet_goal_id`),
  KEY `user_id_fk5_idx` (`user_id`),
  CONSTRAINT `user_id_fk5` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DietGoal`
--

LOCK TABLES `DietGoal` WRITE;
/*!40000 ALTER TABLE `DietGoal` DISABLE KEYS */;
INSERT INTO `DietGoal` VALUES (1,1,'Daily Calorie Deficit',1,'2025-12-01','2025-12-31',2000),(2,2,'Maintain current weight',0,'2025-12-01','2026-03-01',2200),(3,5,'Strictly follow Ketogenic Diet (under 50g Carbs)',0,'2025-12-01','2026-01-01',1800);
/*!40000 ALTER TABLE `DietGoal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Exercise`
--

DROP TABLE IF EXISTS `Exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Exercise` (
  `exercise_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `exercise_goal_id` int DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `distance` decimal(10,2) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`exercise_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `exercise_goal_id_fk3_idx` (`exercise_goal_id`),
  CONSTRAINT `exercise_goal_id_fk3` FOREIGN KEY (`exercise_goal_id`) REFERENCES `ExerciseGoal` (`exercise_goal_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Exercise`
--

LOCK TABLES `Exercise` WRITE;
/*!40000 ALTER TABLE `Exercise` DISABLE KEYS */;
INSERT INTO `Exercise` VALUES (1,1,NULL,'Running',5.50,'06:00:00','06:45:00','2025-12-01'),(2,2,NULL,'Cycling',12.00,'18:30:00','19:15:00','2025-12-01'),(3,1,NULL,'Weightlifting',0.00,'17:00:00','18:00:00','2025-12-02'),(4,4,NULL,'Running',21.10,'07:00:00','09:00:00','2025-12-01'),(5,6,NULL,'Yoga',0.00,'10:00:00','11:00:00','2025-12-03');
/*!40000 ALTER TABLE `Exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExerciseGoal`
--

DROP TABLE IF EXISTS `ExerciseGoal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ExerciseGoal` (
  `exercise_goal_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `completion` tinyint NOT NULL DEFAULT '0',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `goal_distance` decimal(10,2) DEFAULT NULL,
  `goal_type` varchar(45) DEFAULT NULL,
  `goal_time` time DEFAULT NULL,
  PRIMARY KEY (`exercise_goal_id`),
  KEY `user_id_fk7_idx` (`user_id`),
  CONSTRAINT `user_id_fk7` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExerciseGoal`
--

LOCK TABLES `ExerciseGoal` WRITE;
/*!40000 ALTER TABLE `ExerciseGoal` DISABLE KEYS */;
INSERT INTO `ExerciseGoal` VALUES (1,1,'Run 5K weekly',1,'2025-11-01','2026-01-31',5.00,'Running',NULL),(2,2,'Cycle 15 km in under 1 hour',0,'2025-12-01','2025-12-31',15.00,'Cycling','01:00:00'),(3,1,'3 Weight sessions per week',0,'2025-12-01','2025-12-08',0.00,'Weightlifting','00:00:00'),(4,4,'Complete Full Marathon',0,'2024-09-01','2025-03-30',42.20,'Running',NULL),(5,6,'10 hours of Yoga this month',0,'2025-12-01','2025-12-31',0.00,'Yoga','10:00:00');
/*!40000 ALTER TABLE `ExerciseGoal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Follower`
--

DROP TABLE IF EXISTS `Follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Follower` (
  `following_user_id` int NOT NULL,
  `follower_user_id` int NOT NULL,
  PRIMARY KEY (`following_user_id`,`follower_user_id`),
  KEY `follower_user_id_idx` (`follower_user_id`),
  CONSTRAINT `follower_user_id` FOREIGN KEY (`follower_user_id`) REFERENCES `User` (`user_id`),
  CONSTRAINT `following_user_id` FOREIGN KEY (`following_user_id`) REFERENCES `User` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Follower`
--

LOCK TABLES `Follower` WRITE;
/*!40000 ALTER TABLE `Follower` DISABLE KEYS */;
INSERT INTO `Follower` VALUES (4,1),(1,2),(6,2),(2,3);
/*!40000 ALTER TABLE `Follower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Food`
--

DROP TABLE IF EXISTS `Food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Food` (
  `food_id` int NOT NULL AUTO_INCREMENT,
  `food_name` varchar(255) NOT NULL,
  `calories` int DEFAULT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Food`
--

LOCK TABLES `Food` WRITE;
/*!40000 ALTER TABLE `Food` DISABLE KEYS */;
INSERT INTO `Food` VALUES (1,'Oatmeal (1 serving)',160),(2,'Grilled Chicken Breast',180),(3,'Side Salad (light dressing)',100),(4,'Apple',95),(5,'Protein Bar',220),(6,'Spinach Omelette',250),(7,'Brown Rice (1 cup)',215),(8,'Salmon Fillet',300),(9,'Coffee with Cream',50),(10,'Handful of Almonds',170);
/*!40000 ALTER TABLE `Food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `sender` int NOT NULL,
  `recipient` int NOT NULL,
  `message` varchar(1024) NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `sender_idx` (`sender`),
  KEY `recipient_idx` (`recipient`),
  CONSTRAINT `recipient` FOREIGN KEY (`recipient`) REFERENCES `User` (`user_id`),
  CONSTRAINT `sender` FOREIGN KEY (`sender`) REFERENCES `User` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
INSERT INTO `Message` VALUES (1,1,2,'Great job on your cycling today!'),(2,2,1,'Thanks! Did you hit your running goal?'),(3,4,1,'Keep up the great work on the weights, John!'),(4,6,2,'Loved your latest post, what protein bar was that?');
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'jdoe_fit','$2b$10$xyz...','John','Doe','john@example.com','2024-10-01'),(2,'a_smith','$2b$10$abc...','Alice','Smith','alice@example.com','2024-10-05'),(3,'health_nut','$2b$10$def...','Robert','Chen','robert@example.com','2024-11-10'),(4,'marathon_mike','$2b$10$ghi...','Mike','Davis','mike@example.com','2024-09-15'),(5,'keto_karen','$2b$10$jkl...','Karen','Bake','karen@example.com','2024-12-01'),(6,'yoga_yogi','$2b$10$mno...','Yara','Singh','yara@example.com','2024-12-05');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-11  9:18:30
