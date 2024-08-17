package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class AlunoDao {

    public static List<Aluno> getAlunos4Monitor() {

        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'A' ORDER BY nome ASC";
        try (Connection conn = getConexao(); PreparedStatement sta = conn.prepareStatement(sql); ResultSet rs = sta.executeQuery()) {

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
        try (Connection conn = getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (Connection conn = getConexao(); PreparedStatement sta = conn.prepareStatement(sql)) {

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

    public static List<Monitoria> buscarMinhasMonitorias(int idAluno) throws SQLException {

        List<Monitoria> monitorias = new ArrayList<>();

        String sql = "SELECT m.id, d.nome AS disciplina_nome, d.codigo AS disciplina_codigo, " +
                "l.sala, l.capacidade, h.dia_semana, h.horas, " +
                "m.id_monitor, m.id_supervisor, COUNT(im.id_aluno) AS total_inscritos " +
                "FROM inscricao_monitoria im " +
                "JOIN monitoria m ON im.id_monitoria = m.id " +
                "JOIN disciplina d ON m.disciplina = d.codigo " +
                "JOIN local l ON m.local = l.id " +
                "JOIN horario h ON m.horario = h.id " +
                "LEFT JOIN inscricao_monitoria im2 ON m.id = im2.id_monitoria " +
                "WHERE im.id_aluno = ? " +
                "GROUP BY m.id, d.nome, d.codigo, l.sala, l.capacidade, " +
                "h.dia_semana, h.horas, m.id_monitor, m.id_supervisor " +
                "ORDER BY d.nome";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setInt(1, idAluno);

            try (ResultSet rs = sta.executeQuery()) {
                while (rs.next()) {
                    Disciplina disciplina = new Disciplina(rs.getString("disciplina_nome"), rs.getString("disciplina_codigo"));
                    Local local = new Local(rs.getInt("id"), rs.getString("sala"), rs.getInt("capacidade"));
                    Horario horario = new Horario(rs.getString("dia_semana"), rs.getString("horas"));

                    int idMonitor = rs.getInt("id_monitor");
                    int idSupervisor = rs.getInt("id_supervisor");
                    int totalInscritos = rs.getInt("total_inscritos");
                    int idMonitoria = rs.getInt("id");

                    Monitoria monitoria = new Monitoria(disciplina, horario, local, idMonitor, idSupervisor, totalInscritos, idMonitoria);
                    monitorias.add(monitoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monitorias;
    }

    public static boolean inscreverMonitoria(int alunoId, int monitoriaId) throws SQLException {

        String sql = "INSERT INTO inscricao_monitoria (id_aluno, id_monitoria) VALUES (?, ?)";

        try (Connection conn = getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            stmt.setInt(1, alunoId);
            stmt.setInt(2, monitoriaId);

            // Executa a inserção no banco de dados
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean cancelarInscricao(int idMonitoria, int idAluno) {

        String sql = "DELETE FROM inscricao_monitoria WHERE id_monitoria = ? AND id_aluno = ?";
        try (Connection conn = getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idMonitoria);
            pstmt.setInt(2, idAluno);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se pelo menos uma linha foi afetada

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer uma exceção
        }
    }

    // Verifica se o aluno já está inscrito na monitoria
    public static boolean isAlunoInscrito(int alunoId, int monitoriaId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inscricao_monitoria WHERE id_aluno = ? AND id_monitoria = ?";
        try (Connection conn = getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, alunoId);
            pstmt.setInt(2, monitoriaId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o número de inscrições for maior que zero
            }
        }
        return false;
    }
}
