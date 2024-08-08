package model;

import java.util.ArrayList;

public class Monitor extends Aluno {

    private ArrayList<String> disciplina;
    private Horario horario;
    private Local local;

    public Monitor(String matricula, String nome, String email, String senha){
        super(matricula, nome, email, senha);
    }
    public Monitor(String matricula, String nome, String email, String senha, String tipo) {
        super(matricula, nome, email, senha);
        this.tipo = "M";

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