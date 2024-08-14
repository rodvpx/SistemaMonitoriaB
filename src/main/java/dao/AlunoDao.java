package dao;

import model.Aluno;
import model.Monitoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class AlunoDao {

    // Métodos específicos de Aluno

    public static List<Aluno> getAlunos4Monitor() {

        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'A' ORDER BY nome ASC";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql);
             ResultSet rs = sta.executeQuery()) {

            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                Aluno aluno = new Aluno(matricula, nome, email, null);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public static int obterIdAluno(String matriculaAluno) {
        String sql = "SELECT id FROM usuario WHERE matricula = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matriculaAluno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtemos o valor da coluna "id", não "matricula"
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1; // Retorna -1 se o aluno não for encontrado
    }


    public static boolean promoverAluno(Integer id) throws SQLException {
        String sql = "UPDATE usuario SET tipo = 'M' WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {

            sta.setInt(1, id);
            int affectedRows = sta.executeUpdate();

            // Verifica se a atualização afetou alguma linha
            return affectedRows > 0;
        } catch (SQLException ex) {
            // Imprime o stack trace e pode adicionar outras formas de logging
            ex.printStackTrace();
        }
        return false;
    }


    public void visualizarMonitorias(ArrayList<Monitoria> monitorias) {
        // Implementação do método
    }

    public void inscreverMonitoria(Monitoria monitoria) {
        // Implementação do método
    }

    public void solicitarMonitoria(Monitoria monitoria) {
        // Implementação do método
    }

    public void candidatarMonitor(Monitoria monitoria) {
        // Implementação do método
    }
}
