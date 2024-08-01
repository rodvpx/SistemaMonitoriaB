package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class MonitoriaDao {

    public List<Monitoria> buscarTodasMonitorias() {
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

                Monitoria monitoria = new Monitoria(disciplina, horario, local, idMonitor, idSupervisor, totalInscritos);
                monitorias.add(monitoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monitorias;
    }

    public void inscreverAluno(Aluno aluno) {
        // Implementar lógica para inscrever aluno
    }

    public void desinscreverAluno(Aluno aluno) {
        // Implementar lógica para desinscrever aluno
    }
}
