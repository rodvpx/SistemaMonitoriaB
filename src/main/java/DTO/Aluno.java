package DTO;

import java.util.ArrayList;

public class Aluno extends Usuario {

	public Aluno(String matricula, String nome, String email, String senha) {
		super(matricula, nome, email, senha);
	}

	private Monitoria monitoria;


	@Override
	protected boolean validar(){
		try {
		Long.parseLong(this.matricula);
		} catch (NumberFormatException e) {
			System.out.println(e);
			return false;
		}
		return this.matricula != null && this.matricula.length() == 10;
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
