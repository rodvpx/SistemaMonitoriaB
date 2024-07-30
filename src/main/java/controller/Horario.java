package controller;

public class Horario {

    private int id;
    private String diaSemana;
    private String horas;

    // Construtor com parâmetros
    public Horario(String diaSemana, String horas) {
        this.diaSemana = diaSemana;
        this.horas = horas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
}
