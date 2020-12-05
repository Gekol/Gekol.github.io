-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.22-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` varchar(100) NOT NULL,
  `full_name_en` varchar(50) DEFAULT NULL,
  `full_name_ru` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES ('aligeri','dante alig`eri','данте алигьери'),('balsak','onore de balsak','оноре де бальзак'),('bradbury','ray bradbury','рэй брэдберри'),('bulgakov','michael bulgakov','михаил булгаков'),('cooper','james fenimore cooper','джеймс фенимор купер'),('dostoevskyi','fedor dostoevskyi','фёдор достоевский'),('fitzgerald','francis scott fitzgerald','фрэнсис скотт фицджеральд'),('gofman','ernst gofman','эрнст гофман'),('lermontov','michael lermontov','михаил лермонтов'),('nabokov','vladimir nabokov','владимир набоков'),('orwell','george orwell','джордж оруэлл'),('ostin','jane ostin','джейн остин'),('pushkin','alexander pushkin','александр пушкин'),('rowling','joanne rowling','джоан роулинг'),('shakespeare','william shakespeare','уильям шекспир'),('stendal','stendal','стендаль'),('swift','jonathan swift','джонатан сфивт'),('tolstoy','lev tolstoy','лев толстой'),('turgenev','ivan turgenev','иван тургенев');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title_en` varchar(100) DEFAULT NULL,
  `title_ru` varchar(100) DEFAULT NULL,
  `book_src` varchar(100) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `fine` int DEFAULT NULL,
  `author_id` varchar(100) DEFAULT NULL,
  `edition_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  KEY `edition_id` (`edition_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`) ON DELETE CASCADE,
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`edition_id`) REFERENCES `editions` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Harry Potter and the Philosopher\'s stone','Гарри Поттер и философский камень','harry_potter_and_the_philosopher\'s_stone.webp',10,5,'rowling',1),(2,'Harry Potter and the Chamber of Secrets','Гарри Поттер и тайная комната','harry_potter_and_the_chamber_of_secrets.jpg',10,5,'rowling',1),(3,'Harry Potter and the Prisoner of Azkaban','Гарри Поттер и узник Азкабана','harry_potter_and_the_prisoner_of_azkaban.jpg',10,5,'rowling',1),(4,'Harry Potter and the Goblet of Fire','Гарри Поттер и кубок огня','harry_potter_and_the_goblet_of_fire.webp',10,5,'rowling',1),(5,'Harry Potter and the Order of Phoenix','Гарри Поттер и орден феникса','harry_potter_and_the_order_of_phoenix.jpg',10,5,'rowling',1),(6,'Harry Potter and the Half-blood prince','Гарри Поттер и принц-полукровка','harry_potter_and_the_half-blood_prince.webp',10,5,'rowling',1),(7,'Harry Potter and the Deathly Hallows','Гарри Поттер и дары смерти','harry_potter_and_the_deathly_hallows.webp',10,5,'rowling',1),(8,'Fahrenheit 451','451 градус по Фаренгейту','fahrenheit-451.jpg',10,5,'bradbury',1),(9,'1984','1984','1984.png',10,5,'orwell',1),(10,'Anna Karenina','Анна Каренина','anna_karenina.webp',10,5,'tolstoy',1),(11,'Crime and punishment','Преступление и наказание','crime_and_punishment.webp',10,5,'dostoevskyi',1),(12,'Divine comedy','Божественная комедия','divine_comedy.jpg',10,5,'aligeri',1),(13,'Dog\'s heart','Собачье сердце','dog\'s_heart.jpg',10,5,'bulgakov',1),(14,'Evgeniy Onegin','Евгений Онегин','evgeniy_onegin.jpg',10,5,'pushkin',1),(15,'Fathers and children','Отцы и дети','fathers_and_children.jpg',10,5,'turgenev',1),(16,'Gamlet','Гамлет','gamlet.jpeg',10,5,'shakespeare',1),(17,'Gobsek','Гобсек','gobsek.webp',10,5,'balsak',1),(18,'Great Gatsby','Великий Гэтсби','great_gatsby.jpg',10,5,'fitzgerald',1),(19,'Gulliver\'s travels','Путешествие Гулливера','gulliver\'s_travels.jpg',10,5,'swift',1),(20,'Hero of our time','Герой нашего времени','hero_of_our_time.jpg',10,5,'lermontov',1),(21,'Lolita','Лолита','lolita.webp',10,5,'nabokov',1),(22,'Master and Margaryta','Мастер и маргарита','master_and_margaryta.jpg',10,5,'bulgakov',1),(23,'Pride and prejudice','Гордость и предубеждение','pride_and_prejudice.jpg',10,5,'ostin',1),(24,'Red and black','Красное и чёрное','red_and_black.jpg',10,5,'stendal',1),(25,'Romeo and Juliet','Ромео и Джульетта','romeo_i_juliet.jpg',10,5,'shakespeare',1),(26,'The last of the mohicans','Последний из могикан','the_last_of_the_mohicans.jpeg',10,5,'cooper',1),(27,'The nutcracker and the mouse king','Щелкунчик и мышиный король','the_nutcracker_and_the_mouse_king.jpg',10,5,'gofman',1),(28,'War and peace','Война и мир','war_and_peace.jpg',10,5,'tolstoy',1),(29,'The Tragedy of King Lear','Король Лир','default.png',10,10,'shakespeare',1);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog` (
  `book_id` int DEFAULT NULL,
  `book_amount` int DEFAULT NULL,
  KEY `book_id` (`book_id`),
  CONSTRAINT `catalog_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (1,5),(2,8),(3,5),(4,8),(5,5),(6,8),(7,5),(8,7),(9,3),(10,7),(11,5),(12,8),(13,5),(14,7),(15,4),(16,8),(17,5),(18,8),(19,5),(20,8),(21,5),(22,8),(23,5),(24,8),(25,5),(26,8),(27,5),(28,7);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editions`
--

DROP TABLE IF EXISTS `editions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title_en` varchar(50) DEFAULT NULL,
  `title_ru` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editions`
--

LOCK TABLES `editions` WRITE;
/*!40000 ALTER TABLE `editions` DISABLE KEYS */;
INSERT INTO `editions` VALUES (1,'Edition 1','Издание 1','2020-01-01'),(2,'Edition 2','Издание 2','2020-02-01'),(3,'Edition 3','Издание 3','2020-03-01');
/*!40000 ALTER TABLE `editions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderStatuses`
--

DROP TABLE IF EXISTS `orderStatuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderStatuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_en` varchar(50) DEFAULT NULL,
  `status_ru` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderStatuses`
--

LOCK TABLES `orderStatuses` WRITE;
/*!40000 ALTER TABLE `orderStatuses` DISABLE KEYS */;
INSERT INTO `orderStatuses` VALUES (1,'New','Новый'),(2,'Awaiting return','Ожидание возвращения'),(3,'Done','Выполнено');
/*!40000 ALTER TABLE `orderStatuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderTypes`
--

DROP TABLE IF EXISTS `orderTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderTypes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_en` varchar(50) DEFAULT NULL,
  `type_ru` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderTypes`
--

LOCK TABLES `orderTypes` WRITE;
/*!40000 ALTER TABLE `orderTypes` DISABLE KEYS */;
INSERT INTO `orderTypes` VALUES (1,'Subscription','Абонемент'),(2,'Reading hall','Читальный зал');
/*!40000 ALTER TABLE `orderTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `fine` int DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `order_status_id` int DEFAULT NULL,
  `order_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  KEY `order_status_id` (`order_status_id`),
  KEY `order_type_id` (`order_type_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE,
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`order_status_id`) REFERENCES `orderStatuses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`order_type_id`) REFERENCES `orderTypes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,0,'2020-10-09',3,1),(2,3,2,5,'2020-10-15',2,1),(3,4,8,5,'2020-11-12',2,1),(4,4,28,5,'2020-11-12',2,1),(5,4,16,0,'2020-11-12',3,1),(6,1,9,0,NULL,1,1),(7,1,14,0,NULL,1,2),(8,1,15,0,NULL,1,1),(9,1,9,0,NULL,1,1),(10,7,10,0,'2020-10-27',3,2),(11,7,10,0,NULL,1,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriptions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `given` date DEFAULT NULL,
  `expires` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (1,'2020-03-15','2020-12-15'),(2,'2020-03-15','2020-11-15'),(3,'2020-03-15','2020-10-15'),(4,'2020-10-27','2020-11-26');
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userStatuses`
--

DROP TABLE IF EXISTS `userStatuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userStatuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_en` varchar(50) DEFAULT NULL,
  `status_ru` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userStatuses`
--

LOCK TABLES `userStatuses` WRITE;
/*!40000 ALTER TABLE `userStatuses` DISABLE KEYS */;
INSERT INTO `userStatuses` VALUES (1,'Active','Активный'),(2,'Blocked','Заблокированный');
/*!40000 ALTER TABLE `userStatuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userTypes`
--

DROP TABLE IF EXISTS `userTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userTypes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_en` varchar(50) DEFAULT NULL,
  `type_ru` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userTypes`
--

LOCK TABLES `userTypes` WRITE;
/*!40000 ALTER TABLE `userTypes` DISABLE KEYS */;
INSERT INTO `userTypes` VALUES (1,'User','Пользователь'),(2,'Librarian','Библиотекарь'),(3,'Admin','Администратор');
/*!40000 ALTER TABLE `userTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_type_id` int DEFAULT NULL,
  `user_status_id` int DEFAULT NULL,
  `subscription_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_type_id` (`user_type_id`),
  KEY `user_status_id` (`user_status_id`),
  KEY `subscription_id` (`subscription_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`user_type_id`) REFERENCES `userTypes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`user_status_id`) REFERENCES `userStatuses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `users_ibfk_3` FOREIGN KEY (`subscription_id`) REFERENCES `subscriptions` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Darth','Vader','vader@example.com','1111',3,1,1),(2,'Luke','Skywalker','lskywalker@example.com','1111',2,1,1),(3,'Mace','Vindu','vindu@example.com','1111',1,1,1),(4,'Obi-Wan','Kenobi','kenobi@example.com','1111',1,1,1),(5,'Michael','Sokolovsky','123@u.l','M1005mfp',2,2,NULL),(7,'Тарас','Осадчи','123@123','M1005mfp',2,2,4),(8,'Michael','Sokolovsky','hsokolovskyi@gmail.com','M1005mfp',1,1,NULL);
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

-- Dump completed on 2020-11-16  0:24:56
