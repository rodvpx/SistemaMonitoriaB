package model;

public class Local {
    private int id;
    private String sala;
    private int capacidade;

    public Local() {

    }

    public Local(String sala, int capacidade) {
        this.sala = sala;
        this.capacidade = capacidade;
    }

    public Local(int id, String sala, int capacidade) {
        this.id = id;
        this.sala = sala;
        this.capacidade = capacidade;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
