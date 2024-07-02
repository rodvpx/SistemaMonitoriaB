package DTO;

import java.util.ArrayList;

public class Monitoria {

	private Disciplina disciplina;
	private Horario horario;
	private Local local;
	private ArrayList<Aluno> aluno;
	
	public void inscreverAluno (Aluno aluno) {
		
	}
	
	public void desiscreverAluno (Aluno aluno) {
		
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

	public ArrayList<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(ArrayList<Aluno> aluno) {
		this.aluno = aluno;
	}
	
	
}
