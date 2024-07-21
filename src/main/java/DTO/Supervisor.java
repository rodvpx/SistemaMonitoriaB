package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static DAO.conexao.getConexao;

public class Supervisor extends Usuario {

    public Supervisor(String matricula, String nome, String email, String senha) {
        super(matricula, nome, email, senha, "S");
    }

    public void criarMonitoria(Disciplina disciplina, Horario horario, Local local) {
        String matriculaSupervisor = this.getMatricula(); // Supondo que `getMatricula()` retorna a matrícula do supervisor
        Monitoria monitoria = new Monitoria(disciplina, horario, local, matriculaSupervisor);
        try {
            boolean sucesso = monitoria.salvarMonitoria();
            if (sucesso) {
                System.out.println("Monitoria criada com sucesso!");
            } else {
                System.out.println("Erro ao criar monitoria.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar monitoria: " + e.getMessage());
        }
    }


    public boolean promoverAlunoParaMonitor(Aluno aluno) {
        String sql = "UPDATE usuario SET tipo = 'M' WHERE email = ?";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {

            sta.setString(1, aluno.getEmail());
            sta.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void excluirMonitor(Monitor monitor) {
        // Implementação do método
    }

    public void adicionarDisciplina(String cod, String nome) throws SQLException {
        String sql = "insert into disciplina values(?,?,?)";
        try (Connection conn = getConexao();) {
            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setString(1, nome);
            sta.setString(2, cod);
            sta.setString(3, "*");
            sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirDisciplina(String cod) throws SQLException {
        String sql = "delete from disciplina where codigo = ?";
        try (Connection conn = getConexao();) {
            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setString(1, cod);
            sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void designarMonitor(Monitor monitor, Monitor disciplina) {
        // Implementação do método
    }

    public void ajustarHorarioMonitoria(String local, String capacidade) {
        // Implementação do método
    }

    public void excluirHorario(String id) {
    }
}
