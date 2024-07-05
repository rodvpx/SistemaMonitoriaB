package DTO;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int option = 0;
        boolean exit = false;

        do {
            System.out.println("--- SISTEMA MONITORIA ---");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("0 - Sair");

            try {
                option = sc.nextInt();
                sc.nextLine(); // Limpar o buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Limpar o buffer
                continue;
            }

            switch (option) {
                case 0:
                    System.out.println("Saindo...");
                    exit = true;
                    break;

                case 1:
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    Usuario usuario = new Aluno(null, null, email, senha); // Pode ser Aluno, Supervisor ou Monitor

                    try {
                        if (usuario.login(email, senha)) {
                            System.out.println("Login realizado com sucesso!");
                            // Depois do login, diferenciar se é um Aluno, Supervisor ou Monitor e chamar os menus específicos
                            if (usuario instanceof Supervisor) {
                                subMenuSupervisor((Supervisor) usuario, sc);
                            } else {
                                subMenuAluno((Aluno) usuario, sc);
                            }
                        } else {
                            System.out.println("Falha no login. Verifique suas credenciais.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    subMenuUser(sc);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (!exit);

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
                option = sc.nextInt();
                sc.nextLine(); // Limpar o buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Limpar o buffer
                continue;
            }

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
                        e.printStackTrace();
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
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (option != 0);
    }

    public static void subMenuSupervisor(Supervisor supervisor, Scanner sc) {

        int option = 0;

        do {
            System.out.println("--- MENU DO SUPERVISOR ---");
            System.out.println("1 - Promover Aluno para Monitor");
            System.out.println("2 - Outras opções do supervisor");
            System.out.println("0 - Voltar");

            try {
                option = sc.nextInt();
                sc.nextLine(); // Limpar o buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Limpar o buffer
                continue;
            }

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
                    // Outras opções do supervisor
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (option != 0);
    }

    public static void subMenuAluno(Aluno aluno, Scanner sc) {
        // Implementação do menu para Aluno
    }
}
