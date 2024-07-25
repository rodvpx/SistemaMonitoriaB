package DTO;

public class Monitoria {
    private Disciplina disciplina;
    private Horario horario;
    private Local local;
    private int idMonitor;
    private int idSupervisor;
    private int totalInscritos; // Adicione este campo

    // Construtor
    public Monitoria(Disciplina disciplina, Horario horario, Local local, int idMonitor, int idSupervisor, int totalInscritos) {
        this.disciplina = disciplina;
        this.horario = horario;
        this.local = local;
        this.idMonitor = idMonitor;
        this.idSupervisor = idSupervisor;
        this.totalInscritos = totalInscritos;
    }

    public void inscreverAluno(Aluno aluno) {
        // Implementar lógica para inscrever aluno
    }

    public void desinscreverAluno(Aluno aluno) {
        // Implementar lógica para desinscrever aluno
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Horario getHorario() {
        return horario;
    }

    public Local getLocal() {
        return local;
    }

    public int getIdMonitor() {
        return idMonitor;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }

    // Getters e Setters
    public int getTotalInscritos() {
        return totalInscritos;
    }

    public void setTotalInscritos(int totalInscritos) {
        this.totalInscritos = totalInscritos;
    }
}


