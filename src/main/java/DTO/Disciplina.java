package DTO;

public class Disciplina {

    private String nome;
    private String codigo;
    private Monitor monitor;

    // Construtor padrão
    public Disciplina() {}

    // Construtor com parâmetros
    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public void adicionarMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void removerMonitor() {
        this.monitor = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
