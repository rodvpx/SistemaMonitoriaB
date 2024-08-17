package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class MonitoriaDao {

    public static List<Monitoria> buscarTodasMonitorias() {
        List<Monitoria> monitorias = new ArrayList<>();

        String sql = "SELECT d.nome AS disciplina_nome, d.codigo AS disciplina_codigo, l.sala, l.capacidade, " +
                "h.dia_semana, h.horas, " +
                "COUNT(im.id_aluno) AS total_inscritos, m.id AS monitoria_id, m.id_monitor, m.id_supervisor " +
                "FROM monitoria m " +
                "JOIN disciplina d ON m.disciplina = d.codigo " +
                "JOIN local l ON m.local = l.id " +
                "JOIN horario h ON m.horario = h.id " +
                "LEFT JOIN inscricao_monitoria im ON m.id = im.id_monitoria " +
                "GROUP BY m.id, d.nome, d.codigo, l.sala, l.capacidade, h.dia_semana, h.horas, m.id_monitor, m.id_supervisor " +
                "ORDER BY d.nome;";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql);
             ResultSet rs = sta.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(rs.getString("disciplina_nome"), rs.getString("disciplina_codigo"));
                Local local = new Local(rs.getInt("monitoria_id"), rs.getString("sala"), rs.getInt("capacidade"));
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

    public static void excluirMonitoria(Monitoria monitoria) throws SQLException {
        String deleteInscricaoSql = "DELETE FROM inscricao_monitoria WHERE id_monitoria = ?";
        String deleteMonitoriaSql = "DELETE FROM monitoria WHERE id = ?";

        try (Connection conn = getConexao()) {
            conn.setAutoCommit(false); // Inicia a transação

            // Primeiro, exclua as inscrições associadas
            try (PreparedStatement stmt = conn.prepareStatement(deleteInscricaoSql)) {
                stmt.setInt(1, monitoria.getId());
                stmt.executeUpdate();
            }

            // Agora, exclua a monitoria
            try (PreparedStatement stmt = conn.prepareStatement(deleteMonitoriaSql)) {
                stmt.setInt(1, monitoria.getId());
                stmt.executeUpdate();
            }

            conn.commit(); // Confirma a transação

        } catch (SQLException e) {
            // Reverte a transação em caso de erro
            try (Connection conn = getConexao()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            throw e;
        }
    }


    public static boolean editarMonitoria(Monitoria monitoria) throws SQLException {
        try (Connection conn = getConexao()) {
            conn.setAutoCommit(false); // Iniciar transação

            int horarioId;

            // Inserindo o horário
            String sqlHoras = "INSERT INTO horario (dia_semana, horas) VALUES (?, ?)";
            try (PreparedStatement sta = conn.prepareStatement(sqlHoras, Statement.RETURN_GENERATED_KEYS)) {
                sta.setString(1, monitoria.getHorario().getDiaSemana());
                sta.setString(2, monitoria.getHorario().getHoras());
                sta.executeUpdate();

                ResultSet rs = sta.getGeneratedKeys();
                if (rs.next()) {
                    horarioId = rs.getInt(1); // Recupere o ID gerado automaticamente
                } else {
                    conn.rollback(); // Desfaz as operações se ocorrer algum erro
                    throw new SQLException("Erro ao inserir o horário, nenhum ID foi retornado.");
                }
            } catch (SQLException e) {
                conn.rollback(); // Desfaz as operações se ocorrer algum erro
                throw e;
            }

            // Atualizando a monitoria com o ID do novo horário
            String sqlUpdate = "UPDATE monitoria SET disciplina = ?, local = ?, horario = ?, id_monitor = ?, id_supervisor = ? WHERE id = ?";
            try (PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate)) {
                statementUpdate.setInt(1, Integer.parseInt(monitoria.getDisciplina().getCodigo())); // código da disciplina
                statementUpdate.setInt(2, monitoria.getLocal().getId()); // ID do local
                statementUpdate.setInt(3, horarioId); // ID do horário recém-inserido
                statementUpdate.setInt(4, monitoria.getIdMonitor()); // ID do monitor
                statementUpdate.setInt(5, monitoria.getIdSupervisor()); // ID do supervisor
                statementUpdate.setInt(6, monitoria.getId()); // ID da monitoria para identificar qual atualizar

                int rowsUpdated = statementUpdate.executeUpdate();
                if (rowsUpdated == 0) {
                    conn.rollback(); // Reverte a transação em caso de falha
                    throw new SQLException("Nenhuma monitoria foi atualizada. Verifique o ID.");
                }

                conn.commit(); // Confirma a transação
            } catch (SQLException e) {
                conn.rollback(); // Reverte a transação em caso de erro
                throw e;
            }

            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static int contarInscricoes(int idMonitoria) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inscricao_monitoria WHERE id_monitoria = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMonitoria);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Erro ao contar inscrições.");
            }
        }
    }
}
