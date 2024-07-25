package DTO;

public class Local {
    private int id;
    private String sala;
    private int inscritos;
    private int capacidade;

    public Local() {}
    public Local(int id, String sala, int inscritos, int capacidade) {
        this.id = id;
        this.sala = sala;
        this.inscritos = inscritos;
        this.capacidade = capacidade;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }

    public int getInscritos() { return inscritos; }
    public void setInscritos(int inscritos) { this.inscritos = inscritos; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
}
