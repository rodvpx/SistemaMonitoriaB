package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static factory.conexao.getConexao;

public class MonitoriaDao {

    static public List<Monitoria> buscarTodasMonitorias() {

        List<Monitoria> monitorias = new ArrayList<>();

        String sql = "SELECT d.nome AS disciplina_nome, d.codigo AS disciplina_codigo, l.sala, l.capacidade, l.inscritos, h.dia_semana, h.horas, " +
                "COUNT(im.id_aluno) AS total_inscritos, m.id AS monitoria_id, m.id_monitor, m.id_supervisor " +
                "FROM monitoria m " +
                "JOIN disciplina d ON m.disciplina = d.codigo " +
                "JOIN local l ON m.local = l.id " +
                "JOIN horario h ON m.horario = h.id " +
                "LEFT JOIN inscricao_monitoria im ON m.id = im.id_monitoria " +
                "GROUP BY m.id;";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql);
             ResultSet rs = sta.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(rs.getString("disciplina_nome"), rs.getString("disciplina_codigo"));
                Local local = new Local(rs.getInt("monitoria_id"), rs.getString("sala"), rs.getInt("total_inscritos"), rs.getInt("capacidade"));
                Horario horario = new Horario(rs.getString("dia_semana"), rs.getString("horas"));

                int idMonitoria = rs.getInt("monitoria_id");
                int idMonitor = rs.getInt("id_monitor");
                int idSupervisor = rs.getInt("id_supervisor");
                int totalInscritos = rs.getInt("total_inscritos");

                Monitoria monitoria = new Monitoria(disciplina, horario, local, idMonitor, idSupervisor, totalInscritos, idMonitoria);
                monitorias.add(monitoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monitorias;
    }


    public static void criarMonitoria(Disciplina disciplina, Horario horario, Local local, int idMonitor, int idSupervisor) throws SQLException {
        // Conexão única para garantir consistência transacional
        try (Connection conn = getConexao()) {
            conn.setAutoCommit(false); // Iniciar transação

            // Inserindo o horário
            String sqlhoras = "INSERT INTO horario (dia_semana, horas) VALUES (?, ?)";
            try (PreparedStatement sta = conn.prepareStatement(sqlhoras, Statement.RETURN_GENERATED_KEYS)) {
                sta.setString(1, horario.getDiaSemana());
                sta.setString(2, horario.getHoras());
                sta.executeUpdate();

                ResultSet rs = sta.getGeneratedKeys();
                if (rs.next()) {
                    horario.setId(rs.getInt(1)); // Recupere o ID gerado automaticamente
                }
            } catch (SQLException e) {
                conn.rollback(); // Desfaz as operações se ocorrer algum erro
                e.printStackTrace();
                throw e;
            }

            // Inserindo a monitoria
            String sql = "INSERT INTO monitoria (disciplina, horario, local, id_monitor, id_supervisor) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement sta = conn.prepareStatement(sql)) {
                sta.setInt(1, Integer.parseInt(disciplina.getCodigo()));
                sta.setInt(2, horario.getId());
                sta.setInt(3, local.getId());
                sta.setInt(4, idMonitor);
                sta.setInt(5, idSupervisor);
                sta.executeUpdate();

                conn.commit(); // Confirma a transação
            } catch (SQLException e) {
                conn.rollback(); // Desfaz as operações se ocorrer algum erro
                e.printStackTrace();
                throw e;
            }
        }
    }

    public static int excluirMonitoria(Monitoria monitoria) throws SQLException {
        String sql = "DELETE FROM monitoria WHERE id = ?";
        try (Connection conn = getConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement sta = conn.prepareStatement(sql)) {
                sta.setInt(1, monitoria.getId()); // Usa o ID armazenado na monitoria
                int rowsAffected = sta.executeUpdate();
                conn.commit();
                return rowsAffected;
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }



    public void inscreverAluno(Aluno aluno) {
        // Implementar lógica para inscrever aluno
    }

    public void desinscreverAluno(Aluno aluno) {
        // Implementar lógica para desinscrever aluno
    }
}
