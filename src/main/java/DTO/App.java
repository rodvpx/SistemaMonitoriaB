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
                        login(sc);
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
                continue;
            }
        } while (option != 0);

        sc.close();
    }

    public static void login(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario usuario = new Aluno(null, null, email, senha); // Pode ser Aluno, Supervisor ou Monitor

        try {
            String tipoUsuario = usuario.login(email, senha);
            if (tipoUsuario != null) {
                System.out.println("Login realizado com sucesso!");

                if ("S".equals(tipoUsuario)) {
                    Supervisor supervisor = new Supervisor(null, null, email, senha);
                    subMenuSupervisor(supervisor, sc);
                } else {
                    Aluno aluno = new Aluno(null, null, email, senha);
                    subMenuAluno(aluno, sc);
                }

            } else {
                System.out.println("Falha no login. Verifique suas credenciais.");
            }

        } catch (SQLException e) {
            System.out.println("Erro durante o login: " + e.getMessage());
        }
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
                        cadastrarAluno(sc);
                        break;

                    case 2:
                        cadastrarSupervisor(sc);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                continue;
            }
        } while (option != 0);
    }

    public static void cadastrarAluno(Scanner sc) {
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
    }

    public static void cadastrarSupervisor(Scanner sc) {
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
    }

    public static void subMenuSupervisor(Supervisor supervisor, Scanner sc) {
        int option = 0;
        String codigoDisciplina;

        do {
            List<Local> locais;
            List<Disciplina> disciplinas;

            try {
                locais = listarLocais();
                disciplinas = listarDisciplinas();
            } catch (SQLException e) {
                System.out.println("Erro ao carregar disciplinas e locais: " + e.getMessage());
                return;
            }

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
                        promoverAlunoParaMonitor(supervisor, sc);
                        break;

                    case 2:
                        adicionarDisciplinas(disciplinas, sc);
                        break;

                    case 3:
                        adicionarLocais(locais, sc);
                        break;

                    case 4:
                        criarMonitoria(supervisor, disciplinas, locais, sc);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                continue;
            }

        } while (option != 0);
    }

    public static void promoverAlunoParaMonitor(Supervisor supervisor, Scanner sc) {
        boolean promoveuAluno = false;

        do {
            System.out.println("--- PROMOVER ALUNO PARA MONITOR ---");
            System.out.println("1 - Informar email do aluno");
            System.out.println("0 - Voltar");

            try {
                int option = Integer.parseInt(sc.nextLine().trim());

                switch (option) {
                    case 0:
                        return; // Retorna ao menu anterior sem tentar promover aluno

                    case 1:
                        System.out.println("Informe o email do aluno a ser promovido:");
                        String emailAluno = sc.nextLine();

                        Aluno aluno = new Aluno(null, null, emailAluno, null); // Para identificação do aluno

                        if (supervisor.promoverAlunoParaMonitor(aluno)) {
                            System.out.println("Aluno promovido a monitor com sucesso!");
                            promoveuAluno = true;
                        } else {
                            System.out.println("Erro ao promover aluno.");
                        }
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                continue;
            }

        } while (!promoveuAluno);
    }

    public static void adicionarDisciplinas(List<Disciplina> disciplinas, Scanner sc) {
        int subOptionDisciplinas = 0;

        do {
            System.out.println("Lista de Disciplinas:");
            for (Disciplina disciplina : disciplinas) {
                System.out.println(disciplina.getCodigo() + " - " + disciplina.getNome());
            }
            System.out.println("-------------------");
            System.out.println("1 - Adicionar Disciplina");
            System.out.println("0 - Voltar");

            try {
                subOptionDisciplinas = Integer.parseInt(sc.nextLine().trim());

                switch (subOptionDisciplinas) {
                    case 0:
                        break; // Volta ao menu anterior

                    case 1:
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

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                continue;
            }

        } while (subOptionDisciplinas != 0);
    }

    public static void adicionarLocais(List<Local> locais, Scanner sc) {
        int subOptionLocais = 0;

        do {
            System.out.println("Lista de Locais:");
            for (Local local : locais) {
                System.out.println(local.getId() + " - " + local.getSala());
            }
            System.out.println("-------------------");
            System.out.println("1 - Adicionar Local");
            System.out.println("0 - Voltar");

            try {
                subOptionLocais = Integer.parseInt(sc.nextLine().trim());

                switch (subOptionLocais) {
                    case 0:
                        break; // Volta ao menu anterior

                    case 1:
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

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                continue;
            }

        } while (subOptionLocais != 0);
    }

    public static void criarMonitoria(Supervisor supervisor, List<Disciplina> disciplinas, List<Local> locais, Scanner sc) {
        do {
            System.out.println("Cadastro de Monitoria");

            System.out.println("Selecione a disciplina (ou 0 para voltar):");
            for (Disciplina disciplina : disciplinas) {
                System.out.println(disciplina.getCodigo() + " - " + disciplina.getNome());
            }
            System.out.print("Código da disciplina: ");
            String codigoDisciplina = sc.nextLine();

            if (codigoDisciplina.equals("0")) {
                break; // Voltar ao menu anterior
            }

            Disciplina disciplinaEscolhida = disciplinas.stream()
                    .filter(d -> d.getCodigo().equals(codigoDisciplina))
                    .findFirst()
                    .orElse(null);

            if (disciplinaEscolhida == null) {
                System.out.println("Disciplina não encontrada.");
                continue;
            }

            System.out.print("Dia da semana:");
            String diaSemana = sc.nextLine();
            System.out.print("Horário:");
            String horas = sc.nextLine();
            Horario horarioEscolhido = new Horario(diaSemana, horas);

            System.out.println("Selecione o local:");
            for (Local local : locais) {
                System.out.println(local.getId() + " - " + local.getSala());
            }
            System.out.print("ID do local: ");
            int idLocal = Integer.parseInt(sc.nextLine().trim());
            Local localEscolhido = locais.stream()
                    .filter(l -> l.getId() == idLocal)
                    .findFirst()
                    .orElse(null);

            if (localEscolhido == null) {
                System.out.println("Local não encontrado.");
                continue;
            }

            supervisor.criarMonitoria(disciplinaEscolhida, horarioEscolhido, localEscolhido);
            break;

        } while (true);
    }

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

    public static boolean inserirDisciplina(String nome, String codigo) {
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

    public static boolean inserirLocais(String sala, String capacidade) {
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
