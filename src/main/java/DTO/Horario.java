package DTO;

public class Horario {

    private int id;
    private String diaSemana;
    private String periodo;

    public Horario(String diaSemana, String periodo) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaSemanda() {
        return diaSemana;
    }

    public void setDiaSemanda(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
