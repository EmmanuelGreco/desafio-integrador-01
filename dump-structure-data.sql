-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: desafio_integrador_01
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (1,'Suspenso'),(2,'Ciencia ficción'),(3,'Acción'),(4,'Aventura'),(5,'Misterio'),(6,'Crimen'),(7,'Terror'),(8,'Fantasía'),(9,'Infantil'),(10,'Drama'),(11,'Romance'),(12,'Comedia'),(13,'Musical'),(14,'Bélico'),(15,'Época/Historia');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(40) NOT NULL,
  `director` varchar(25) NOT NULL,
  `url` varchar(80) NOT NULL,
  `imagen` longblob,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (1,'Oppenheimer','Christopher Nolan','https://www.oppenheimermovie.com',NULL),(2,'Inception','Christopher Nolan','https://www.warnerbros.com/movies/inception',NULL),(3,'Interstellar','Christopher Nolan','https://www.warnerbros.co.uk/movies/interstellar',NULL),(4,'Dunkirk','Christopher Nolan','http://unlock.dunkirkmovie.com/intl/nl/',NULL),(5,'Tenet','Christopher Nolan','https://www.tenetfilm.com',NULL),(6,'Memento','Christopher Nolan','https://www.imdb.com/title/tt0209144/',NULL),(7,'The Prestige','Christopher Nolan','https://www.warnerbros.co.uk/movies/prestige',NULL),(8,'Batman: Begins','Christopher Nolan','https://www.warnerbros.com/movies/batman-begins',NULL),(9,'Batman: The Dark Knight','Christopher Nolan','https://www.warnerbros.com/movies/dark-knight',NULL),(10,'Batman: The Dark Knight Rises','Christopher Nolan','https://www.warnerbros.com/movies/dark-knight-rises',NULL),(11,'Prisoners','Denis Villeneuve','https://www.warnerbros.com/movies/prisoners',NULL),(12,'Arrival','Denis Villeneuve','https://www.imdb.com/title/tt2543164/',NULL),(13,'Blade Runner 2049','Denis Villeneuve','https://www.bladerunner2049movie.com/',NULL),(14,'Enemy','Denis Villeneuve','https://www.imdb.com/title/tt2316411/',NULL),(15,'Transcendence','Wally Pfister','https://www.warnerbros.com/movies/transcendence',NULL),(16,'The Little Things','John Lee Hancock','https://www.warnerbros.com/movies/little-things',NULL),(17,'A Monster Calls','Juan Antonio Bayona','https://www.uphe.com/movies/a-monster-calls',NULL),(18,'The Whale','Darren Aronofsky','https://a24films.com/films/the-whale',NULL),(19,'The Shape Of Water','Guillermo del Toro','https://www.20thcenturystudios.com/movies/the-shape-of-water',NULL),(20,'Poor Things','Yorgos Lanthimos','https://www.poorthingsfilm.co.uk/home/',NULL),(21,'The Mummy','Stephen Sommers','https://www.uphe.com/movies/the-mummy-1999',NULL),(22,'The Mummy Returns','Stephen Sommers','https://www.uphe.com/movies/the-mummy-returns',NULL),(23,'The Mummy: Tomb of the Dragon Emperor','Rob Cohen','https://www.uphe.com/movies/the-mummy-tomb-of-the-dragon-emperor',NULL),(24,'Barbie','Greta Gerwig','https://www.warnerbros.com/movies/barbie',NULL),(25,'The Avengers','Joss Whedon','https://www.marvel.com/movies/the-avengers',NULL),(26,'Avengers: Age of Ultron','Joss Whedon','https://www.marvel.com/movies/avengers-age-of-ultron',NULL),(27,'Avengers: Infinity War','Russo brothers','https://www.marvel.com/movies/avengers-infinity-war',NULL),(28,'Avengers: Endgame','Russo brothers','https://www.marvel.com/movies/avengers-endgame',NULL),(29,'La La Land','Damien Chazelle','https://www.lionsgate.com/movies/la-la-land',NULL),(30,'The Notebook','Nick Cassavetes','https://www.warnerbros.com/movies/notebook',NULL);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas_generos`
--

DROP TABLE IF EXISTS `peliculas_generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas_generos` (
  `codigoPelicula` int NOT NULL,
  `idGenero` int NOT NULL,
  PRIMARY KEY (`codigoPelicula`,`idGenero`),
  KEY `idGenero` (`idGenero`),
  CONSTRAINT `peliculas_generos_ibfk_1` FOREIGN KEY (`codigoPelicula`) REFERENCES `peliculas` (`codigo`),
  CONSTRAINT `peliculas_generos_ibfk_2` FOREIGN KEY (`idGenero`) REFERENCES `generos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas_generos`
--

LOCK TABLES `peliculas_generos` WRITE;
/*!40000 ALTER TABLE `peliculas_generos` DISABLE KEYS */;
INSERT INTO `peliculas_generos` VALUES (1,1),(2,1),(3,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(13,1),(15,1),(16,1),(2,2),(3,2),(5,2),(12,2),(13,2),(15,2),(25,2),(26,2),(27,2),(28,2),(2,3),(4,3),(5,3),(8,3),(9,3),(10,3),(21,3),(22,3),(23,3),(25,3),(26,3),(27,3),(28,3),(3,4),(8,4),(9,4),(10,4),(21,4),(22,4),(23,4),(6,5),(11,5),(12,5),(14,5),(16,5),(6,6),(11,6),(16,6),(13,8),(15,8),(17,8),(19,8),(21,8),(22,8),(23,8),(24,8),(25,8),(26,8),(27,8),(28,8),(17,9),(7,10),(9,10),(11,10),(12,10),(14,10),(15,10),(16,10),(18,10),(19,10),(20,10),(29,10),(30,10),(19,11),(20,11),(29,11),(30,11),(20,12),(24,12),(29,12),(29,13),(1,14),(4,14),(1,15),(4,15);
/*!40000 ALTER TABLE `peliculas_generos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 21:54:47
