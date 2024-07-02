package DTO;

<<<<<<< HEAD
import java.sql.SQLException;
=======
>>>>>>> origin/master
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int option = 0;
        boolean exit = false;

        do {

            System.out.println("--- SISTEMA MONITORIA ---");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("0 - para sair");

            try {
                option = sc.nextInt();
                sc.nextLine(); // Limpar o buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Limpar o buffer
                continue; // Volta para o início do loop
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

<<<<<<< HEAD
=======
                    // Busca usuário com o email informado
                    Usuario usuario = Usuario.buscarPorEmail(usuarios, email);
                    if (usuario != null) {
                        usuario.login(email, senha);
                    } else {
                        System.out.println("Login inválido. Por favor, verifique seu email e senha.");
                    }
>>>>>>> origin/master
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
                continue; // Volta para o início do loop
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

                    // Cria um novo aluno e cadastra na lista de usuários
                    Aluno aluno = new Aluno(matriculaAluno, nomeAluno, emailAluno, senhaAluno);
<<<<<<< HEAD
                    try {
                        if (aluno.cadastrarNovoUsuario()) {
                            System.out.println("Aluno cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar aluno.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } //oi
=======
                    aluno.cadastrarNovoUsuario(usuarios);
>>>>>>> origin/master
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

                    // Cria um novo supervisor e cadastra na lista de usuários
                    Supervisor supervisor = new Supervisor(codigoSupervisor, nomeSupervisor, emailSupervisor,
                            senhaSupervisor);
<<<<<<< HEAD
                    //supervisor.cadastrarNovoUsuario(usuarios);
=======
                    supervisor.cadastrarNovoUsuario(usuarios);
>>>>>>> origin/master
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (option != 0);
    }
}
