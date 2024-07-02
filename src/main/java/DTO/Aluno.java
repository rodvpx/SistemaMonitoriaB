package DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static DAO.conexao.getConexao;

public class Aluno extends Usuario {

    public Aluno(String matricula, String nome, String email, String senha) {
        super(matricula, nome, email, senha);
    }

    private Monitoria monitoria;

    @Override
    protected boolean validar() {
        return false;
    }

    @Override
    protected boolean cadastrarNovoUsuario() throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha, matricula, tipo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {

            sta.setString(1, getNome());
            sta.setString(2, getEmail());
            sta.setString(3, getSenha());
            sta.setString(4, getMatricula());
            sta.setString(5, "A");
            sta.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        //oi
    }


    @Override
    protected boolean login() {
        return false;
    }

    public void visualizarMonitorias(ArrayList<Monitoria> monitoria) {

    }

    public void inscreverMonitoria(Monitoria monitoria) {

    }

    public void solicitarMonitoria(Monitoria disciplina) {

    }

    public void candidatarMonitor(Monitoria disciplina) {

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }
}
