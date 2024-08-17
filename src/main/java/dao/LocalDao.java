package dao;

import model.Local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class LocalDao {

    public static boolean adicionaLocal(Local local) throws SQLException {

        String sql = "insert into local (sala, capacidade) values(?,?)";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, local.getSala());
            sta.setInt(2, local.getCapacidade());
            sta.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean excluirLocal(int id) throws SQLException {
        String sql = "DELETE FROM local WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setInt(1, id);
            int affectedRows = sta.executeUpdate(); // Chama executeUpdate apenas uma vez

            // Retorna true se pelo menos uma linha foi afetada (excluída)
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static Local obterLocal(String nomeSala) {

        String sql = "SELECT id, sala, capacidade FROM local WHERE sala = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeSala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String sala = rs.getString("sala");
                int capacidade = rs.getInt("capacidade");
                return new Local(id, sala, capacidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Local> getSalas() {

        List<Local> locais = new ArrayList<>();

        String sqlSalas = "SELECT id, sala FROM local ORDER BY sala";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlSalas);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("sala");
                locais.add(new Local(id, nome, 0));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locais;
    }


    public static boolean verificarLocalExiste(String sala) throws SQLException {

        String sql = "Select count(*) from local where sala = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;

    }

    public static List<Local> mostrarLocal() throws SQLException {

        List<Local> locais = new ArrayList<>();

        String sql = "SELECT id, sala, capacidade FROM local order by sala asc ";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Local local = new Local();
                local.setId(rs.getInt("id"));
                local.setSala(rs.getString("sala"));
                local.setCapacidade(rs.getInt("capacidade"));
                locais.add(local);
            }
        }
        return locais;
    }
}
