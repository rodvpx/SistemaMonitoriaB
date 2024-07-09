package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static DAO.conexao.getConexao;

public class Monitoria {
    private Disciplina disciplina;

    private Horario horario;
    private Local local;
    String codigoSupervisor;
    private ArrayList<Aluno> alunos;

    public Monitoria(Disciplina disciplina, Horario horario, Local local, String codigoSupervisor) {
        this.disciplina = disciplina;
        this.horario = horario;
        this.local = local;
        this.codigoSupervisor = codigoSupervisor;
        this.alunos = new ArrayList<>();
    }


    public boolean salvarMonitoria() throws SQLException {
        String sql = "insert into monitoria (disciplina, horario, local, codigo_supervisor) values (?, ?, ?, ?)";

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, disciplina.getCodigo());
            sta.setInt(2, horario.getId());
            sta.setInt(3, local.getId());
            sta.setString(4, codigoSupervisor);
            sta.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void inscreverAluno(Aluno aluno) {

    }

    public void desiscreverAluno(Aluno aluno) {

    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getCodigoSupervisor() {
        return codigoSupervisor;
    }

    public void setCodigoSupervisor(String codigoSupervisor) {
        this.codigoSupervisor = codigoSupervisor;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
}
