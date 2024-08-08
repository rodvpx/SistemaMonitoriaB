package dao;

import model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class DisciplinaDao {

    public static int obterCodigoDisciplina(String nomeDisciplina) {
        String sql = "SELECT codigo FROM disciplina WHERE nome = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeDisciplina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String[] getDisciplinas() {
        List<String> disciplinas = new ArrayList<>();

        String sqlDisciplinas = "SELECT nome FROM disciplina ORDER BY nome";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlDisciplinas);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                disciplinas.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplinas.toArray(new String[0]); // Convertendo a lista para um array de Strings
    }

    private boolean verificarCodigoExistente(String codigo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM disciplina WHERE codigo = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Retorna true se o código já existir
            }
        }
        return false; // Retorna false se o código não existir
    }

     public static List<Disciplina> mostrarDisciplina() throws SQLException {
        // Consultar as disciplinas do banco de dados
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM disciplina ORDER BY codigo ASC";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setCodigo(rs.getString("codigo"));
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
        }
        return disciplinas;
    }

}
