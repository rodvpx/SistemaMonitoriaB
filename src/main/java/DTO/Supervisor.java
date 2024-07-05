package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static DAO.conexao.getConexao;

public class Supervisor extends Usuario {

	public Supervisor(String matricula, String nome, String email, String senha) {
		super(matricula, nome, email, senha, "S");
	}

	public boolean promoverAlunoParaMonitor(Aluno aluno) {
		String sql = "UPDATE usuario SET tipo = 'M' WHERE email = ?";
		try (Connection conn = getConexao();
			 PreparedStatement sta = conn.prepareStatement(sql)) {

			sta.setString(1, aluno.getEmail());
			sta.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Métodos específicos de Supervisor

	public void excluirMonitor(Monitor monitor) {
		// Implementação do método
	}

	public void adicionarDisciplina(Monitor disciplina) {
		// Implementação do método
	}

	public void excluirDisciplina(Monitor disciplina) {
		// Implementação do método
	}

	public void designarMonitor(Monitor monitor, Monitor disciplina) {
		// Implementação do método
	}

	public void ajustarHorarioMonitoria(Monitoria monitoria, Monitoria horario, Monitoria local) {
		// Implementação do método
	}
}
