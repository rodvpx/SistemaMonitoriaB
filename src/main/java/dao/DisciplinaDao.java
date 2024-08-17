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

    public static boolean adicionarDisciplina(Disciplina disciplina) throws SQLException {

        String sql = "insert into disciplina values(?,?,?)";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, disciplina.getNome());
            sta.setInt(2, Integer.parseInt(disciplina.getCodigo()));
            sta.setString(3, "*");
            sta.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean excluirDisciplina(String codigo) throws SQLException {

        String sql = "DELETE FROM disciplina WHERE codigo = ?";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {

            sta.setInt(1, Integer.parseInt(codigo));

            int affectedRows = sta.executeUpdate();

            // Retorna true se pelo menos uma linha foi afetada (excluída)
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Disciplina[] getDisciplinas() {

        List<Disciplina> disciplinas = new ArrayList<>();

        String sqlDisciplinas = "SELECT codigo, nome FROM disciplina ORDER BY nome";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlDisciplinas);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nome = rs.getString("nome");
                disciplinas.add(new Disciplina(nome, codigo)); // Cria uma nova Disciplina com código e nome
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplinas.toArray(new Disciplina[0]); // Converte a lista para um array de Disciplina
    }


    public static boolean verificarCodigoExistente(String codigo) throws SQLException {

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
        String sql = "SELECT codigo, nome FROM disciplina ORDER BY nome ASC";
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
