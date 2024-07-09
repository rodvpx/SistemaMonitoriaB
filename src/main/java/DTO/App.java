package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DAO.conexao.getConexao;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int option = 0;

        do {
            System.out.println("--- SISTEMA MONITORIA ---");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("0 - Sair");

            try {
                option = Integer.parseInt(sc.nextLine().trim());

                switch (option) {
                    case 0:
                        System.out.println("Saindo...");
                        return;

                    case 1:
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Senha: ");
                        String senha = sc.nextLine();

                        Usuario usuario = new Aluno(null, null, email, senha); // Pode ser Aluno, Supervisor ou Monitor

                        try {
                            String tipoUsuario = usuario.login(email, senha);
                            if (tipoUsuario != null) {
                                System.out.println("Login realizado com sucesso!");

                                // Chamar o submenu adequado com base no tipo de usuário retornado
                                if ("S".equals(tipoUsuario)) {
                                    Supervisor supervisor = new Supervisor(null, null, email, senha);
                                    subMenuSupervisor(supervisor, sc);
                                }
                                Aluno aluno = new Aluno(null, null, email, senha);
                                subMenuAluno(aluno, sc);

                            } else {
                                System.out.println("Falha no login. Verifique suas credenciais.");
                            }

                        } catch (SQLException e) {
                            System.out.println("Erro durante o login: " + e.getMessage());
                        }
                        break;


                    case 2:
                        subMenuUser(sc);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Somente números inteiros.");
                sc.nextLine(); // Limpar o buffer
                continue;
            }
        } while (option != 0);

        sc.close();
    }

    public static void subMenuUser(Scanner sc) {

        int option = 0;

        do {
            System.out.println("--- MENU DE USUÁRIOS ---");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Supervisor");
            System.out.println("0 - Voltar");

            try {
                option = Integer.parseInt(sc.nextLine().trim());

                switch (option) {
                    case 0:
                        return;

                    case 1:
                        System.out.println("Informe a matrícula do aluno:");
                        String matriculaAluno = sc.nextLine();
                        System.out.println("Informe o nome do aluno:");
                        String nomeAluno = sc.nextLine();
                        System.out.println("Informe o email do aluno:");
                        String emailAluno = sc.nextLine();
                        System.out.println("Informe a senha do aluno:");
                        String senhaAluno = sc.nextLine();

                        Aluno aluno = new Aluno(matriculaAluno, nomeAluno, emailAluno, senhaAluno);
                        try {
                            if (aluno.cadastrarNovoUsuario()) {
                                System.out.println("Aluno cadastrado com sucesso!");
                            } else {
                                System.out.println("Erro ao cadastrar aluno.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro durante o cadastro do aluno: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Informe o código do supervisor:");
                        String codigoSupervisor = sc.nextLine();
                        System.out.println("Informe o nome do supervisor:");
                        String nomeSupervisor = sc.nextLine();
                        System.out.println("Informe o email do supervisor:");
                        String emailSupervisor = sc.nextLine();
                        System.out.println("Informe a senha do supervisor:");
                        String senhaSupervisor = sc.nextLine();

                        Supervisor supervisor = new Supervisor(codigoSupervisor, nomeSupervisor, emailSupervisor, senhaSupervisor);
                        try {
                            if (supervisor.cadastrarNovoUsuario()) {
                                System.out.println("Supervisor cadastrado com sucesso!");
                            } else {
                                System.out.println("Erro ao cadastrar supervisor.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro durante o cadastro do supervisor: " + e.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Limpar o buffer
                continue;
            }
        } while (option != 0);
    }

    public static void subMenuSupervisor(Supervisor supervisor, Scanner sc) throws SQLException {

        List<Disciplina> disciplinas = listarDisciplinas();
        List<Local> locais = listarLocais();

        int option = 0;

        do {
            System.out.println("--- MENU DO SUPERVISOR ---");
            System.out.println("1 - Promover Aluno para Monitor");
            System.out.println("2 - Adicionar Disciplinas");
            System.out.println("3 - Adicionar Locais");
            System.out.println("4 - Criar Monitoria");
            System.out.println("0 - Voltar");

            try {
                option = Integer.parseInt(sc.nextLine().trim());

                switch (option) {
                    case 0:
                        return;

                    case 1:
                        System.out.println("Informe o email do aluno a ser promovido:");
                        String emailAluno = sc.nextLine();

                        Aluno aluno = new Aluno(null, null, emailAluno, null); // Para identificação do aluno

                        if (supervisor.promoverAlunoParaMonitor(aluno)) {
                            System.out.println("Aluno promovido a monitor com sucesso!");
                        } else {
                            System.out.println("Erro ao promover aluno.");
                        }
                        break;

                    case 2:
                        // ADICIONAR DISCIPLINAS
                        System.out.println("Lista de Disciplinas:");
                        for (Disciplina disciplina : disciplinas) {
                            System.out.println(disciplina.getCodigo() + " - " + disciplina.getNome());
                        }

                        System.out.println("Nome da Disciplina:");
                        String nomeDisciplina = sc.nextLine();
                        System.out.println("Código da Disciplina:");
                        String codigoDisciplina = sc.nextLine();

                        if (inserirDisciplina(nomeDisciplina, codigoDisciplina)) {
                            System.out.println("Disciplina inserida com sucesso!");
                        } else {
                            System.out.println("Erro ao inserir disciplina.");
                        }
                        break;

                    case 3:
                        // ADICIONAR LOCAIS
                        System.out.println("Lista de Locais:");
                        for (Local local : locais) {
                            System.out.println(local.getId() + " - " + local.getSala());
                        }

                        System.out.println("Sala:");
                        String sala = sc.nextLine();
                        System.out.println("Capacidade:");
                        String capacidade = sc.nextLine();

                        if (inserirLocais(sala, capacidade)) {
                            System.out.println("Local inserido com sucesso!");
                        } else {
                            System.out.println("Erro ao inserir local.");
                        }
                        break;

                    case 4:
                        // CRIAR MONITORIA
                        System.out.println("Cadastro de Monitoria");

                        System.out.println("Selecione a disciplina:");
                        for (Disciplina disciplina : disciplinas) {
                            System.out.println(disciplina.getCodigo() + " - " + disciplina.getNome());
                        }
                        System.out.print("Código da disciplina: ");
                        codigoDisciplina = sc.nextLine();
                        Disciplina disciplinaEscolhida = null;

                        for (Disciplina disciplina : disciplinas) {
                            if (disciplina.getCodigo().equals(codigoDisciplina)) {
                                disciplinaEscolhida = disciplina;
                                break;
                            }
                        }

                        if (disciplinaEscolhida == null) {
                            System.out.println("Disciplina não encontrada.");
                            break;
                        }

                        System.out.println("Selecione o horário:");
                        System.out.print("Dia da semana:");
                        String diaSemana = sc.nextLine();
                        System.out.print("Período:");
                        String periodo = sc.nextLine();
                        Horario horarioEscolhido = new Horario(diaSemana, periodo);

                        System.out.println("Selecione o local:");
                        for (Local local : locais) {
                            System.out.println(local.getId() + " - " + local.getSala());
                        }
                        System.out.print("ID do local: ");
                        int idLocal = Integer.parseInt(sc.nextLine().trim());
                        Local localEscolhido = null;

                        for (Local local : locais) {
                            if (local.getId() == idLocal) {
                                localEscolhido = local;
                                break;
                            }
                        }

                        if (localEscolhido == null) {
                            System.out.println("Local não encontrado.");
                            break;
                        }

                        supervisor.criarMonitoria(disciplinaEscolhida, horarioEscolhido, localEscolhido);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Limpar o buffer
                continue;
            }
        } while (option != 0);
    }

    // Método para listar todas as disciplinas disponíveis
    public static List<Disciplina> listarDisciplinas() throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();

        String sql = "SELECT codigo, nome FROM disciplina";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setCodigo(rs.getString("codigo"));
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
        }

        return disciplinas;
    }

    // Método para listar todos os locais disponíveis
    public static List<Local> listarLocais() throws SQLException {
        List<Local> locais = new ArrayList<>();

        String sql = "SELECT id, sala, capacidade FROM local";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Local local = new Local();
                local.setId(rs.getInt("id"));
                local.setSala(rs.getString("sala"));
                local.setCapacidade(rs.getInt("capacidade"));
                locais.add(local);
            }
        }

        return locais;
    }

    public static boolean inserirDisciplina(String nome, String codigo) throws SQLException {
        String sql = "INSERT INTO disciplina (nome, codigo, monitor) VALUES (?, ?, ?)";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, nome);
            sta.setString(2, codigo);
            sta.setString(3, "*"); // valor padrão para monitor, ajuste conforme necessário
            int rowsAffected = sta.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir disciplina: " + e.getMessage());
            return false;
        }
    }

    public static boolean inserirLocais(String sala, String capacidade) throws SQLException {
        String sql = "INSERT INTO local (sala, capacidade) VALUES (?, ?)";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, sala);
            sta.setString(2, capacidade);
            int rowsAffected = sta.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir local: " + e.getMessage());
            return false;
        }
    }

    public static void subMenuAluno(Aluno aluno, Scanner sc) {
        // Implementação do menu para Aluno
    }
}
