package DTO;

public class Horario {

    private int id;
    private String diaSemana;
    private String horas;

    public Horario(String diaSemana, String horas) {
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
        return horas;
    }

    public void setPeriodo(String horas) {
        this.horas = horas;
    }
}
