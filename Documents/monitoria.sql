-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 17/08/2024 às 23:22
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `monitoria`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `nome` varchar(50) NOT NULL,
  `codigo` int(11) NOT NULL,
  `monitor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `disciplina`
--

INSERT INTO `disciplina` (`nome`, `codigo`, `monitor`) VALUES
('Programação I', 1, '*'),
('Programação II', 2, '*'),
('Matemática Discreta', 3, '*'),
('Banco de Dados I', 4, '*'),
('Logica', 5, '*'),
('Engenharia de Software', 6, '*'),
('Programação Python I', 7, '*'),
('Desenvolvimento Web I', 8, '*'),
('Estrutura De Dados I', 10, '*');

-- --------------------------------------------------------

--
-- Estrutura para tabela `horario`
--

CREATE TABLE `horario` (
  `id` int(11) NOT NULL,
  `dia_semana` varchar(30) DEFAULT NULL,
  `horas` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `horario`
--

INSERT INTO `horario` (`id`, `dia_semana`, `horas`) VALUES
(1, 'segunda', '15:00'),
(2, 'Segunda-feira', '08:00'),
(3, 'Quarta-feira', '14:00'),
(4, 'Sexta-feira', '10:30'),
(6, 'Segunda-feira', '13:00'),
(7, 'Quarta-feira', '15:00'),
(8, 'Segunda-feira', '13:00'),
(9, 'Terça-feira', '15:00'),
(10, 'Quarta-feira', '16:30'),
(11, 'Quinta-feira', '08:30'),
(12, 'Segunda-feira', '15:30'),
(13, 'Terça-feira', '13:00'),
(14, 'Terça-feira', '13:00'),
(15, 'Sexta-feira', '13:00'),
(16, 'Quinta-feira', '17:00'),
(17, 'Terça-feira', '07:00'),
(18, 'Quinta-feira', '17:00'),
(19, 'Quarta-feira', '13:00'),
(20, 'Quarta-feira', '16:00'),
(21, 'Terça-feira', '15:00'),
(26, 'Sexta-feira', '13:00'),
(27, 'Quinta-feira', '08:30'),
(28, 'Quinta-feira', '08:30'),
(29, 'Sexta-feira', '15:30'),
(30, 'Quinta-feira', '20:00'),
(31, 'Terça-feira', '16:33'),
(32, 'Quarta-feira', '16:30'),
(33, 'Quinta-feira', '17:00'),
(34, 'Quinta-feira', '17:00'),
(35, 'Segunda-feira', '13:00'),
(36, 'Segunda-feira', '13:00'),
(37, 'Segunda-feira', '13:00'),
(38, 'Segunda-feira', '13:00'),
(39, 'Segunda-feira', '08:00'),
(40, 'Segunda-feira', '10:00'),
(41, 'Terça-feira', '09:10'),
(42, 'Terça-feira', '08:00'),
(43, 'Quinta-feira', '13:00'),
(44, 'Quinta-feira', '13:00'),
(45, 'Quinta-feira', '15:00'),
(46, 'Quinta-feira', '15:00'),
(47, 'Quinta-feira', '15:00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `inscricao_monitoria`
--

CREATE TABLE `inscricao_monitoria` (
  `id` int(11) NOT NULL,
  `id_monitoria` int(11) NOT NULL,
  `id_aluno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `inscricao_monitoria`
--

INSERT INTO `inscricao_monitoria` (`id`, `id_monitoria`, `id_aluno`) VALUES
(10, 32, 1),
(15, 32, 3),
(17, 32, 13),
(12, 32, 22),
(11, 33, 1),
(20, 33, 13),
(16, 34, 3),
(21, 34, 13),
(13, 34, 22),
(23, 35, 2),
(18, 35, 13),
(24, 36, 2),
(19, 36, 13);

-- --------------------------------------------------------

--
-- Estrutura para tabela `local`
--

CREATE TABLE `local` (
  `id` int(11) NOT NULL,
  `sala` varchar(20) NOT NULL,
  `capacidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `local`
--

INSERT INTO `local` (`id`, `sala`, `capacidade`) VALUES
(1, 'Lab 1', 30),
(2, 'Lab 2', 25),
(4, 'Lab 8', 30),
(5, 'Lab 5', 27),
(9, 'Lab 3', 25),
(10, 'Lab 4', 35),
(12, 'Lab 6', 15);

-- --------------------------------------------------------

--
-- Estrutura para tabela `monitor`
--

CREATE TABLE `monitor` (
  `id` int(11) NOT NULL,
  `disciplina` varchar(50) NOT NULL,
  `horario` varchar(10) NOT NULL,
  `local` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `monitor`
--

INSERT INTO `monitor` (`id`, `disciplina`, `horario`, `local`) VALUES
(1, 'Programação I', '', '');

-- --------------------------------------------------------

--
-- Estrutura para tabela `monitoria`
--

CREATE TABLE `monitoria` (
  `id` int(11) NOT NULL,
  `disciplina` int(11) DEFAULT NULL,
  `horario` int(11) DEFAULT NULL,
  `local` int(11) DEFAULT NULL,
  `id_monitor` int(11) NOT NULL,
  `id_supervisor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `monitoria`
--

INSERT INTO `monitoria` (`id`, `disciplina`, `horario`, `local`, `id_monitor`, `id_supervisor`) VALUES
(32, 4, 39, 1, 22, 48),
(33, 8, 40, 1, 3, 48),
(34, 6, 41, 2, 28, 48),
(35, 10, 42, 2, 44, 48),
(36, 5, 44, 4, 6, 48);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `matricula` varchar(12) NOT NULL,
  `tipo` enum('A','M','S') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `matricula`, `tipo`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'M'),
(2, 'Fernando Silva', 'fernando.silva123@gmail.com', 'senha123', '1234567890', 'A'),
(3, 'Amanda Santos', 'amanda.santos456@hotmail.com', 'senha456', '0987654321', 'M'),
(4, 'Pedro Oliveira', 'pedro.oliveira789@gmail.com', 'senha789', '1357924680', 'A'),
(5, 'Ana Souza', 'ana.souza123@hotmail.com', 'senhaabc', '2468013579', 'A'),
(6, 'Carlos Pereira', 'carlos.pereira456@gmail.com', 'senhaxyz', '9876543210', 'M'),
(7, 'Mariana Lima', 'mariana.lima789@hotmail.com', 'senha456', '3579136280', 'A'),
(8, 'Ant?nio Santos', 'antonio.santos123@gmail.com', 'senha789', '7654321098', 'A'),
(9, 'Rafaela Oliveira', 'rafaela.oliveira456@hotmail.com', 'senhaxyz', '1592648703', 'A'),
(10, 'Luiz Souza', 'luiz.souza789@gmail.com', 'senhaabc', '3141592653', 'A'),
(11, 'Gabriel Pereira', 'gabriel.pereira123@hotmail.com', 'senha123', '2718281828', 'A'),
(12, 'Juliana Almeida', 'juliana.almeida456@gmail.com', 'senha456', '8182838485', 'A'),
(13, 'Rodrigo Santos', 'rodrigo.santos789@hotmail.com', 'senha789', '2384626433', 'M'),
(14, 'Camila Lima', 'camila.lima123@gmail.com', 'senhaxyz', '8462643383', 'A'),
(15, 'Mateus Oliveira', 'mateus.oliveira456@hotmail.com', 'senhaabc', '2795028841', 'A'),
(16, 'Patr?cia Souza', 'patricia.souza123@gmail.com', 'senha123', '9716939937', 'A'),
(17, 'Felipe Pereira', 'felipe.pereira456@hotmail.com', 'senha456', '5105820974', 'A'),
(18, 'Carolina Almeida', 'carolina.almeida789@gmail.com', 'senha789', '5923078164', 'A'),
(19, 'Andr? Santos', 'andre.santos123@hotmail.com', 'senhaxyz', '0628620899', 'A'),
(20, 'Vanessa Lima', 'vanessa.lima456@gmail.com', 'senhaabc', '8628034825', 'A'),
(21, 'Marcelo Oliveira', 'marcelo.oliveira789@hotmail.com', 'senha123', '3482534211', 'M'),
(22, 'Aline Souza', 'aline.souza123@gmail.com', 'senha456', '7067982148', 'M'),
(23, 'Guilherme Pereira', 'guilherme.pereira789@hotmail.com', 'senha789', '1134754543', 'A'),
(24, 'Roberta Santos', 'roberta.santos456@gmail.com', 'senhaxyz', '0263605611', 'A'),
(25, 'Jos? Lima', 'jose.lima789@hotmail.com', 'senhaabc', '7465754577', 'A'),
(26, 'Isabela Oliveira', 'isabela.oliveira123@gmail.com', 'senha123', '2738658976', 'A'),
(27, 'Lucas Almeida', 'lucas.almeida456@hotmail.com', 'senha456', '4239050288', 'A'),
(28, 'Beatriz Souza', 'beatriz.souza789@gmail.com', 'senha789', '9878343675', 'M'),
(29, 'Thiago Pereira', 'thiago.pereira123@hotmail.com', 'senhaxyz', '4626433832', 'A'),
(30, 'Renata Santos', 'renata.santos789@gmail.com', 'senhaabc', '7950288419', 'A'),
(31, 'Vin?cius Lima', 'vinicius.lima123@hotmail.com', 'senha123', '8128498086', 'A'),
(32, 'Tatiane Oliveira', 'tatiane.oliveira456@gmail.com', 'senha456', '6028999348', 'A'),
(33, 'Leandro Souza', 'leandro.souza789@hotmail.com', 'senha789', '0038376725', 'A'),
(34, 'Cristina Pereira', 'cristina.pereira123@gmail.com', 'senhaxyz', '3421170679', 'A'),
(35, 'Marcela Santos', 'marcela.santos789@hotmail.com', 'senhaabc', '8214808651', 'A'),
(36, 'Paulo Lima', 'paulo.lima123@gmail.com', 'senha123', '3282306647', 'A'),
(37, 'Ana Clara Oliveira', 'anaclara.oliveira789@hotmail.com', 'senha456', '2384702047', 'A'),
(38, 'Ricardo Almeida', 'ricardo.almeida123@gmail.com', 'senha789', '6659334461', 'A'),
(39, 'Isadora Souza', 'isadora.souza456@hotmail.com', 'senhaxyz', '3173343997', 'A'),
(40, 'Henrique Pereira', 'henrique.pereira789@gmail.com', 'senhaabc', '7070817284', 'A'),
(41, 'Larissa Santos', 'larissa.santos123@hotmail.com', 'senha123', '8193261179', 'A'),
(42, 'Gustavo Lima', 'gustavo.lima456@gmail.com', 'senha456', '0228416268', 'A'),
(43, 'Let?cia Oliveira', 'leticia.oliveira789@hotmail.com', 'senha789', '0348253421', 'A'),
(44, 'Carla Souza', 'carla.souza123@gmail.com', 'senhaxyz', '8745648122', 'M'),
(45, 'F?bio Pereira', 'fabio.pereira456@hotmail.com', 'senhaabc', '3883539242', 'A'),
(46, 'Mariana Lima', 'mariana.lima789@gmail.com', 'senha123', '1190341027', 'A'),
(47, 'Diego Santos', 'diego.santos456@hotmail.com', 'senha456', '5477051221', 'M'),
(48, 'Jos? Lima', 'jose.lima123@gmail.com', 'senha123', '746575457712', 'S'),
(49, 'Isabela Oliveira', 'isabela.oliveira456@hotmail.com', 'senha456', '273865897612', 'S'),
(50, 'Lucas Almeida', 'lucas.almeida789@gmail.com', 'senha789', '423905028812', 'S'),
(51, 'Beatriz Souza', 'beatriz.souza123@hotmail.com', 'senhaxyz', '987834367512', 'S'),
(52, 'Thiago Pereira', 'thiago.pereira789@gmail.com', 'senhaabc', '462643383212', 'S'),
(53, 'Renata Santos', 'renata.santos123@hotmail.com', 'senha123', '795028841912', 'S'),
(54, 'Vin?cius Lima', 'vinicius.lima456@gmail.com', 'senha456', '812849808612', 'S'),
(55, 'Tatiane Oliveira', 'tatiane.oliveira789@hotmail.com', 'senha789', '602899934812', 'S'),
(56, 'Leandro Souza', 'leandro.souza123@gmail.com', 'senhaxyz', '003837672512', 'S'),
(57, 'Cristina Pereira', 'cristina.pereira456@hotmail.com', 'senhaabc', '342117067912', 'S'),
(58, 'Marcela Santos', 'marcela.santos789@gmail.com', 'senha123', '821480865112', 'S'),
(59, 'Paulo Lima', 'paulo.lima123@hotmail.com', 'senha456', '328230664712', 'S'),
(60, 'Ana Clara Oliveira', 'anaclara.oliveira456@gmail.com', 'senha789', '238470204712', 'S'),
(61, 'Ricardo Almeida', 'ricardo.almeida789@hotmail.com', 'senhaxyz', '665933446112', 'S'),
(62, 'Isadora Souza', 'isadora.souza123@gmail.com', 'senhaabc', '317334399712', 'S'),
(63, 'Henrique Pereira', 'henrique.pereira789@hotmail.com', 'senha123', '707081728412', 'S'),
(64, 'Larissa Santos', 'larissa.santos456@gmail.com', 'senha456', '819326117912', 'S'),
(65, 'Gustavo Lima', 'gustavo.lima789@hotmail.com', 'senha789', '022841626812', 'S'),
(66, 'Let?cia Oliveira', 'leticia.oliveira123@gmail.com', 'senhaxyz', '034825342112', 'S'),
(67, 'Carla Souza', 'carla.souza456@hotmail.com', 'senhaabc', '874564812212', 'S'),
(68, 'F?bio Pereira', 'fabio.pereira789@gmail.com', 'senha123', '388353924212', 'S'),
(69, 'Diego Santos', 'diego.santos123@gmail.com', 'senha789', '547705122112', 'S'),
(70, 'Amanda Oliveira', 'amanda.oliveira456@hotmail.com', 'senhaxyz', '304891210312', 'S'),
(71, 'Roberto Souza', 'roberto.souza789@gmail.com', 'senhaabc', '629177684412', 'S'),
(72, 'Laura Pereira', 'laura.pereira123@hotmail.com', 'senha123', '112849035612', 'S'),
(73, 'Thales Lima', 'thales.lima789@gmail.com', 'senha456', '789512981612', 'S'),
(74, 'Sabrina Oliveira', 'sabrina.oliveira123@hotmail.com', 'senha789', '889625034212', 'S'),
(75, 'Raphael Souza', 'raphael.souza456@gmail.com', 'senhaxyz', '181231186412', 'S'),
(76, 'Nat?lia Santos', 'natalia.santos789@hotmail.com', 'senhaabc', '594592736412', 'S'),
(77, 'Bruno Lima', 'bruno.lima123@gmail.com', 'senha123', '237043538512', 'S'),
(78, 'Luana Oliveira', 'luana.oliveira456@hotmail.com', 'senha456', '876464338312', 'S'),
(79, 'Leonardo Souza', 'leonardo.souza789@gmail.com', 'senha789', '226726271312', 'S'),
(80, 'Camila Pereira', 'camila.pereira123@hotmail.com', 'senhaxyz', '283202316812', 'S'),
(81, 'Lucas Lima', 'lucas.lima789@gmail.com', 'senhaabc', '482534211712', 'S'),
(82, 'Beatriz Oliveira', 'beatriz.oliveira456@hotmail.com', 'senha123', '923758530812', 'S'),
(83, 'Andr? Santos', 'andre.santos789@gmail.com', 'senha456', '974944592312', 'S'),
(84, 'Fernanda Souza', 'fernanda.souza123@hotmail.com', 'senha789', '847564823912', 'S'),
(85, 'Gustavo Pereira', 'gustavo.pereira456@gmail.com', 'senhaxyz', '264338327912', 'S'),
(86, 'Vanessa Lima', 'vanessa.lima789@hotmail.com', 'senhaabc', '780780434312', 'S'),
(87, 'Miguel Oliveira', 'miguel.oliveira123@gmail.com', 'senha123', '830119491312', 'S'),
(88, 'Carolina Santos', 'carolina.santos456@hotmail.com', 'senha456', '284422825712', 'S'),
(89, 'Pedro Souza', 'pedro.souza789@gmail.com', 'senha789', '430711872912', 'S'),
(90, 'TesteAluno', 'testealuno@gmail.com', '123', '7852361230', 'A'),
(91, 'TesteSupervisor', 'testesupervisor@gmail.com', '123', '785236123000', 'S'),
(92, 'TesteNovoSUp', 'supteste', '123', '000111222123', 'S'),
(93, 'novotestesup', 'supervisortestes', '123', '789789789789', 'S'),
(94, 'testandonovosup', 'supstestes', '123', '147147147147', 'S'),
(95, 'testando', 'testesuper', '123', '852852852852', 'S'),
(96, 'testecadastroaluno', 'tertekk', '123', '1231231231', 'A'),
(97, 'testesupcadastro', 'testesupca', '123', '789789741123', 'S'),
(98, 'Milena Silva', 'milena.silva@gmail.com', '123456', '8989562319', 'A');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices de tabela `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `inscricao_monitoria`
--
ALTER TABLE `inscricao_monitoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_monitoria` (`id_monitoria`,`id_aluno`),
  ADD KEY `fk_aluno` (`id_aluno`);

--
-- Índices de tabela `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `monitor`
--
ALTER TABLE `monitor`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `monitoria`
--
ALTER TABLE `monitoria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_monitor` (`id_monitor`),
  ADD KEY `fk_supervisor` (`id_supervisor`),
  ADD KEY `fk_disciplina` (`disciplina`),
  ADD KEY `fk_horario` (`horario`),
  ADD KEY `fk_local` (`local`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `matricula` (`matricula`),
  ADD UNIQUE KEY `matricula_2` (`matricula`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `horario`
--
ALTER TABLE `horario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de tabela `inscricao_monitoria`
--
ALTER TABLE `inscricao_monitoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de tabela `local`
--
ALTER TABLE `local`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `monitor`
--
ALTER TABLE `monitor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `monitoria`
--
ALTER TABLE `monitoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `inscricao_monitoria`
--
ALTER TABLE `inscricao_monitoria`
  ADD CONSTRAINT `fk_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `fk_monitoria` FOREIGN KEY (`id_monitoria`) REFERENCES `monitoria` (`id`),
  ADD CONSTRAINT `inscricao_monitoria_ibfk_1` FOREIGN KEY (`id_monitoria`) REFERENCES `monitoria` (`id`),
  ADD CONSTRAINT `inscricao_monitoria_ibfk_2` FOREIGN KEY (`id_aluno`) REFERENCES `usuario` (`id`);

--
-- Restrições para tabelas `monitoria`
--
ALTER TABLE `monitoria`
  ADD CONSTRAINT `fk_disciplina` FOREIGN KEY (`disciplina`) REFERENCES `disciplina` (`codigo`),
  ADD CONSTRAINT `fk_horario` FOREIGN KEY (`horario`) REFERENCES `horario` (`id`),
  ADD CONSTRAINT `fk_local` FOREIGN KEY (`local`) REFERENCES `local` (`id`),
  ADD CONSTRAINT `fk_monitor` FOREIGN KEY (`id_monitor`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `fk_supervisor` FOREIGN KEY (`id_supervisor`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
