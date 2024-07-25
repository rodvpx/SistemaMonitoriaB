package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static DAO.conexao.getConexao;

public class Supervisor extends Usuario {

    public Supervisor(String matricula, String nome, String email, String senha) {
        super(matricula, nome, email, senha, "S");
    }

    public void criarMonitoria(Disciplina disciplina, Horario horario, Local local,int idMonitor, int idSupervisor) throws SQLException {
        String sql = "insert into monitoria values (?,?,?,?,?)";
        try (Connection conexao = getConexao();){
            PreparedStatement sta = conexao.prepareStatement(sql);
            sta.setString(1, disciplina.getCodigo());
            sta.setInt(2, horario.getId());
            sta.setInt(3, local.getId());
            sta.setInt(4, idMonitor);
            sta.setInt(5, idSupervisor);
            sta.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public boolean promoverAlunoParaMonitor(Aluno aluno) {
        String sql = "UPDATE usuario SET tipo = 'M' WHERE matricula = ?";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {

            sta.setString(1, aluno.getMatricula());
            sta.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void excluirMonitor(String matricula) {
        String sql = "UPDATE usuario SET tipo = 'A' WHERE matricula = ?";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, matricula);
            sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void adicionarLocal(String sala, int capacidade) {

        String sql = "insert into local (sala, capacidade) values(?,?)";
        try (Connection conn = getConexao();) {
            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setString(1, sala);
            sta.setInt(2, capacidade);
            sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void excluirLocal(int id) {

        String sql = "delete from local where id = ?";
        try (Connection conn = getConexao();) {
            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setInt(1, id);
            sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
