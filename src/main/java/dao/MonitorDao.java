package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class MonitorDao {

    public static List<Monitor> mostrarMonitores() {

        List<Monitor> monitores = new ArrayList<>();

        String sql = "SELECT * FROM usuario WHERE tipo = 'M' ORDER BY nome ASC";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql);
             ResultSet rs = sta.executeQuery()) {

            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Monitor monitor = new Monitor(matricula, nome, email, null, null);
                monitores.add(monitor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monitores;
    }


    public static int obterIdMonitor(String nomeMonitor) {

        String sql = "SELECT id FROM usuario WHERE nome = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeMonitor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static String[] getMonitores() {

        List<String> monitores = new ArrayList<>();

        String sql = "SELECT nome FROM usuario WHERE tipo = 'M' ORDER BY nome ASC";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                monitores.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monitores.toArray(new String[0]); // Convertendo a lista para um array de Strings
    }

    public static String getMonitorId(Integer id) {

        String nomeMonitor = null;
        String sql = "SELECT nome FROM usuario WHERE id = ? AND tipo = 'M'";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Define o ID do monitor no SQL
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomeMonitor = rs.getString("nome");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nomeMonitor;  // Retorna o nome do monitor ou null se não for encontrado
    }

    public static boolean excluirMonitor(String matricula) throws SQLException {

        String sql = "UPDATE usuario SET tipo = 'A' WHERE matricula = ?";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, matricula);
            int affectedRows = sta.executeUpdate();

            // Verifica se a atualização afetou alguma linha
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
