-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: monitoria
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `matricula` varchar(12) NOT NULL,
  `tipo` enum('A','M','S') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `matricula` (`matricula`),
  UNIQUE KEY `matricula_2` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','adminmonitoria','admin',NULL),(2,'Fernando Silva','fernando.silva123@gmail.com','senha123','1234567890','A'),(3,'Amanda Santos','amanda.santos456@hotmail.com','senha456','0987654321','A'),(4,'Pedro Oliveira','pedro.oliveira789@gmail.com','senha789','1357924680','A'),(5,'Ana Souza','ana.souza123@hotmail.com','senhaabc','2468013579','A'),(6,'Carlos Pereira','carlos.pereira456@gmail.com','senhaxyz','9876543210','A'),(7,'Mariana Lima','mariana.lima789@hotmail.com','senha456','3579136280','A'),(8,'Ant?nio Santos','antonio.santos123@gmail.com','senha789','7654321098','A'),(9,'Rafaela Oliveira','rafaela.oliveira456@hotmail.com','senhaxyz','1592648703','A'),(10,'Luiz Souza','luiz.souza789@gmail.com','senhaabc','3141592653','A'),(11,'Gabriel Pereira','gabriel.pereira123@hotmail.com','senha123','2718281828','A'),(12,'Juliana Almeida','juliana.almeida456@gmail.com','senha456','8182838485','A'),(13,'Rodrigo Santos','rodrigo.santos789@hotmail.com','senha789','2384626433','A'),(14,'Camila Lima','camila.lima123@gmail.com','senhaxyz','8462643383','A'),(15,'Mateus Oliveira','mateus.oliveira456@hotmail.com','senhaabc','2795028841','A'),(16,'Patr?cia Souza','patricia.souza123@gmail.com','senha123','9716939937','A'),(17,'Felipe Pereira','felipe.pereira456@hotmail.com','senha456','5105820974','A'),(18,'Carolina Almeida','carolina.almeida789@gmail.com','senha789','5923078164','A'),(19,'Andr? Santos','andre.santos123@hotmail.com','senhaxyz','0628620899','A'),(20,'Vanessa Lima','vanessa.lima456@gmail.com','senhaabc','8628034825','A'),(21,'Marcelo Oliveira','marcelo.oliveira789@hotmail.com','senha123','3482534211','A'),(22,'Aline Souza','aline.souza123@gmail.com','senha456','7067982148','A'),(23,'Guilherme Pereira','guilherme.pereira789@hotmail.com','senha789','1134754543','A'),(24,'Roberta Santos','roberta.santos456@gmail.com','senhaxyz','0263605611','A'),(25,'Jos? Lima','jose.lima789@hotmail.com','senhaabc','7465754577','A'),(26,'Isabela Oliveira','isabela.oliveira123@gmail.com','senha123','2738658976','A'),(27,'Lucas Almeida','lucas.almeida456@hotmail.com','senha456','4239050288','A'),(28,'Beatriz Souza','beatriz.souza789@gmail.com','senha789','9878343675','A'),(29,'Thiago Pereira','thiago.pereira123@hotmail.com','senhaxyz','4626433832','A'),(30,'Renata Santos','renata.santos789@gmail.com','senhaabc','7950288419','A'),(31,'Vin?cius Lima','vinicius.lima123@hotmail.com','senha123','8128498086','A'),(32,'Tatiane Oliveira','tatiane.oliveira456@gmail.com','senha456','6028999348','A'),(33,'Leandro Souza','leandro.souza789@hotmail.com','senha789','0038376725','A'),(34,'Cristina Pereira','cristina.pereira123@gmail.com','senhaxyz','3421170679','A'),(35,'Marcela Santos','marcela.santos789@hotmail.com','senhaabc','8214808651','A'),(36,'Paulo Lima','paulo.lima123@gmail.com','senha123','3282306647','A'),(37,'Ana Clara Oliveira','anaclara.oliveira789@hotmail.com','senha456','2384702047','A'),(38,'Ricardo Almeida','ricardo.almeida123@gmail.com','senha789','6659334461','A'),(39,'Isadora Souza','isadora.souza456@hotmail.com','senhaxyz','3173343997','A'),(40,'Henrique Pereira','henrique.pereira789@gmail.com','senhaabc','7070817284','A'),(41,'Larissa Santos','larissa.santos123@hotmail.com','senha123','8193261179','A'),(42,'Gustavo Lima','gustavo.lima456@gmail.com','senha456','0228416268','A'),(43,'Let?cia Oliveira','leticia.oliveira789@hotmail.com','senha789','0348253421','A'),(44,'Carla Souza','carla.souza123@gmail.com','senhaxyz','8745648122','A'),(45,'F?bio Pereira','fabio.pereira456@hotmail.com','senhaabc','3883539242','A'),(46,'Mariana Lima','mariana.lima789@gmail.com','senha123','1190341027','A'),(47,'Diego Santos','diego.santos456@hotmail.com','senha456','5477051221','A'),(48,'Jos? Lima','jose.lima123@gmail.com','senha123','746575457712','S'),(49,'Isabela Oliveira','isabela.oliveira456@hotmail.com','senha456','273865897612','S'),(50,'Lucas Almeida','lucas.almeida789@gmail.com','senha789','423905028812','S'),(51,'Beatriz Souza','beatriz.souza123@hotmail.com','senhaxyz','987834367512','S'),(52,'Thiago Pereira','thiago.pereira789@gmail.com','senhaabc','462643383212','S'),(53,'Renata Santos','renata.santos123@hotmail.com','senha123','795028841912','S'),(54,'Vin?cius Lima','vinicius.lima456@gmail.com','senha456','812849808612','S'),(55,'Tatiane Oliveira','tatiane.oliveira789@hotmail.com','senha789','602899934812','S'),(56,'Leandro Souza','leandro.souza123@gmail.com','senhaxyz','003837672512','S'),(57,'Cristina Pereira','cristina.pereira456@hotmail.com','senhaabc','342117067912','S'),(58,'Marcela Santos','marcela.santos789@gmail.com','senha123','821480865112','S'),(59,'Paulo Lima','paulo.lima123@hotmail.com','senha456','328230664712','S'),(60,'Ana Clara Oliveira','anaclara.oliveira456@gmail.com','senha789','238470204712','S'),(61,'Ricardo Almeida','ricardo.almeida789@hotmail.com','senhaxyz','665933446112','S'),(62,'Isadora Souza','isadora.souza123@gmail.com','senhaabc','317334399712','S'),(63,'Henrique Pereira','henrique.pereira789@hotmail.com','senha123','707081728412','S'),(64,'Larissa Santos','larissa.santos456@gmail.com','senha456','819326117912','S'),(65,'Gustavo Lima','gustavo.lima789@hotmail.com','senha789','022841626812','S'),(66,'Let?cia Oliveira','leticia.oliveira123@gmail.com','senhaxyz','034825342112','S'),(67,'Carla Souza','carla.souza456@hotmail.com','senhaabc','874564812212','S'),(68,'F?bio Pereira','fabio.pereira789@gmail.com','senha123','388353924212','S'),(69,'Diego Santos','diego.santos123@gmail.com','senha789','547705122112','S'),(70,'Amanda Oliveira','amanda.oliveira456@hotmail.com','senhaxyz','304891210312','S'),(71,'Roberto Souza','roberto.souza789@gmail.com','senhaabc','629177684412','S'),(72,'Laura Pereira','laura.pereira123@hotmail.com','senha123','112849035612','S'),(73,'Thales Lima','thales.lima789@gmail.com','senha456','789512981612','S'),(74,'Sabrina Oliveira','sabrina.oliveira123@hotmail.com','senha789','889625034212','S'),(75,'Raphael Souza','raphael.souza456@gmail.com','senhaxyz','181231186412','S'),(76,'Nat?lia Santos','natalia.santos789@hotmail.com','senhaabc','594592736412','S'),(77,'Bruno Lima','bruno.lima123@gmail.com','senha123','237043538512','S'),(78,'Luana Oliveira','luana.oliveira456@hotmail.com','senha456','876464338312','S'),(79,'Leonardo Souza','leonardo.souza789@gmail.com','senha789','226726271312','S'),(80,'Camila Pereira','camila.pereira123@hotmail.com','senhaxyz','283202316812','S'),(81,'Lucas Lima','lucas.lima789@gmail.com','senhaabc','482534211712','S'),(82,'Beatriz Oliveira','beatriz.oliveira456@hotmail.com','senha123','923758530812','S'),(83,'Andr? Santos','andre.santos789@gmail.com','senha456','974944592312','S'),(84,'Fernanda Souza','fernanda.souza123@hotmail.com','senha789','847564823912','S'),(85,'Gustavo Pereira','gustavo.pereira456@gmail.com','senhaxyz','264338327912','S'),(86,'Vanessa Lima','vanessa.lima789@hotmail.com','senhaabc','780780434312','S'),(87,'Miguel Oliveira','miguel.oliveira123@gmail.com','senha123','830119491312','S'),(88,'Carolina Santos','carolina.santos456@hotmail.com','senha456','284422825712','S'),(89,'Pedro Souza','pedro.souza789@gmail.com','senha789','430711872912','S');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-05 20:41:08
