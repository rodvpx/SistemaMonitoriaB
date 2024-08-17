package model;

public class Monitoria {
    private Disciplina disciplina;
    private Horario horario;
    private Local local;
    private int idMonitor;
    private int idSupervisor;
    private int totalInscritos;
    private int id;

    // Construtor
    public Monitoria(Disciplina disciplina, Horario horario, Local local, int idMonitor, int idSupervisor, int totalInscritos, int id) {
        this.disciplina = disciplina;
        this.horario = horario;
        this.local = local;
        this.idMonitor = idMonitor;
        this.idSupervisor = idSupervisor;
        this.totalInscritos = totalInscritos;
        this.id = id;
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

    public int getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(int idMonitor) {
        this.idMonitor = idMonitor;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public int getTotalInscritos() {
        return totalInscritos;
    }

    public void setTotalInscritos(int totalInscritos) {
        this.totalInscritos = totalInscritos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


