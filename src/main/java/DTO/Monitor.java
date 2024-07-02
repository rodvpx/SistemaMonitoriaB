package DTO;

import java.util.ArrayList;

public class Monitor extends Aluno {

	private ArrayList<String> disciplina;
	private Horario horario;
	private Local local;

	public Monitor(String matricula, String nome, String email, String senha) {
		super(matricula, nome, email, senha);
		//TODO Auto-generated constructor stub
	}
	
	public void definirHorario(Aluno disciplina, Horario horario, Local local) {
		
	}
	
	public void consultarHorarios (ArrayList<Horario> horario) {
		
	}

	public ArrayList<String> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(ArrayList<String> disciplina) {
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
}
