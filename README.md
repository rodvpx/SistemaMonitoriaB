# 🎓 Sistema de Monitoria

O **Sistema de Monitoria** é uma plataforma desenvolvida para facilitar o gerenciamento e a participação em monitorias acadêmicas dentro de instituições de ensino. O projeto foi desenvolvido como parte da disciplina de **Programação Orientada a Objetos** (POO) no 3º período do curso de **Sistemas de Informação**.

## 👥 Desenvolvedores

* Rodrigo Simão
* Kauê De Souza

## 👨‍🏫 Professora

* Cristiane de Fátima dos Santos Cardoso

---

## 📌 Visão Geral

A aplicação permite que alunos se inscrevam em monitorias, sejam promovidos a monitores, e que supervisores (professores ou coordenadores) organizem e administrem disciplinas, locais e monitorias.

---

## 📚 Funcionalidades

### 👩‍🎓 Para Alunos

* Visualização de monitorias disponíveis
* Inscrição em monitorias
* Visualização de sessões em andamento

### 🧑‍🏫 Para Supervisores

* Cadastro de disciplinas
* Cadastro de locais (salas, laboratórios, etc.)
* Criação e gerenciamento de monitorias
* Promoção de alunos a monitores
* Definição de horários e locais das sessões
* Controle de participantes e sessões realizadas `(não implementado)`

### 🧑‍💻 Para Monitores

* Visualização e organização de sessões sob sua responsabilidade
* Controle de presença e andamento das sessões

---

## 🛠️ Tecnologias Utilizadas

* **Java** – Lógica principal da aplicação
* **JavaFX** – Interface gráfica do sistema
* **MySQL** – Banco de dados relacional
* **JDBC** – Comunicação com o banco de dados
* **Maven** – Gerenciador de dependências e build

---

## 🧱 Estrutura de Dados

* **Diagrama de Classes** e **Modelo MER** foram utilizados para planejar e implementar uma estrutura bem organizada, com entidades como `Aluno`, `Monitor`, `Supervisor`, `Disciplina`, `Monitoria`, `Local`, entre outras.

### Diagrama de Classes
![Diagrama de Classes](/Documents/img/diagrama-classes.png)

### Modelo MER (Entidade Relacionamento)
![Modelo MER](/Documents/img/diagrama-mer.png)
---

## 🚀 Como Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/rodvpx/SistemaMonitoriaB.git
   ```

2. Importe como projeto Maven em sua IDE (Eclipse, IntelliJ, etc.)

3. Configure o banco de dados MySQL:

    * Crie um schema chamado `sistema_monitoria`
    * É importante criar o banco de dados `CREATE DATABASE monitoria;`
    * Atualizar as credenciais de acesso em `conexao.java` localizado em `src/main/java/factory/conexao.java`
    * Execute o script SQL localizado na pasta `Documents/database` (ou crie as tabelas com base no MER)

4. Atualize o arquivo de configuração com seu `usuário` e `senha` do banco

5. Rode o projeto executando a classe principal

---

## 🖼️ Telas do Sistema

### Tela Principal
![Tela Principal](/Documents/img/tela-inicial.png)

### Tela de Login
![Tela Login](/Documents/img/login.png)

### Tela de Cadastro
![Tela Cadastro](/Documents/img/cadastro-usuario.png)

### Painel do Supervisor

#### Tela Principal
![Tela Supervisor](/Documents/img/painel-supervisor-home.png)

#### Cadastrar Monitoria
![Tela Monitorias](/Documents/img/supervisor-cadastrar-monitoria.png)

#### Cadastrar Monitor
![Tela Monitores](/Documents/img/supervisor-promover-monitor.png)

#### Disciplinas
![Tela Disciplinas](/Documents/img/supervisor-disciplinas.png)

#### Locais
![Tela Locais](/Documents/img/supervisor-locais.png)

### Painel do Aluno

#### Tela Principal
![Tela Principal Aluno](/Documents/img/aluno-home.png)

#### Monitorias Inscritas
![Minhas Monitorias](/Documents/img/aluno-inscrições.png)

---