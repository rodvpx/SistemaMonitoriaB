package dao;

import model.Aluno;
import model.Horario;
import model.Local;

import java.util.ArrayList;

public class MonitorDao {
    public void definirHorario(Aluno disciplina, Horario horario, Local local) {

    }

    public void consultarHorarios(ArrayList<Horario> horario) {

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
