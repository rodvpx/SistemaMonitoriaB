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

    public static Local obterLocal(String nomeSala) {
        String sql = "SELECT id, sala, capacidade, inscritos FROM local WHERE sala = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeSala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String sala = rs.getString("sala");
                int capacidade = rs.getInt("capacidade");
                int inscritos = rs.getInt("inscritos");
                return new Local(id, sala, capacidade, inscritos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getSalas() {
        List<String> salas = new ArrayList<>();

        String sqlSalas = "SELECT sala FROM local ORDER BY sala";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlSalas);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                salas.add(rs.getString("sala"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salas.toArray(new String[0]); // Convertendo a lista para um array de Strings
    }

    private boolean verificarLocalExiste(String sala) throws SQLException {
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

        String sql = "SELECT id, sala, capacidade, inscritos FROM local order by id asc ";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Local local = new Local();
                local.setId(rs.getInt("id"));
                local.setSala(rs.getString("sala"));
                local.setCapacidade(rs.getInt("capacidade"));
                local.setInscritos(rs.getInt("inscritos"));
                locais.add(local);
            }
        }
        return locais;
    }
}
